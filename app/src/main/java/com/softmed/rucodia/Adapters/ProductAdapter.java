package com.softmed.rucodia.Adapters;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.softmed.rucodia.R;
import com.softmed.rucodia.Dom.entities.ProductList;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private static final String TAG = ProductAdapter.class.getSimpleName();
    private Context mContext;
    private List<ProductList> mProducts;
    private OnItemClickListener mOnItemClickListener;
    private OnItemSaleListener mOnItemSaleListener;
    private OnItemDeleteListener mOnItemDeleteListener;

    public ProductAdapter(Context context, List<ProductList> products) {
        mContext = context;
        mProducts = products;
        Log.d(TAG,"list data size = "+mProducts.size());
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnItemSaleListener(OnItemSaleListener listener) {
        mOnItemSaleListener = listener;
    }

    public void setOnItemDeleteListener(OnItemDeleteListener listener) {
        mOnItemDeleteListener = listener;
    }

    public void refreshData(List<ProductList> products) {
        mProducts.clear();
        mProducts.addAll(products);
        notifyDataSetChanged();
    }

    public void emptyData() {
        mProducts.clear();
        notifyDataSetChanged();
    }

    /**
     * Method that gets invoked when the user presses the 'Sale' button on the popup menu.
     * This decreases the selected product qty by 1 only if it wouldn't result a negative quantity.
     * @param position - The ArrayList position of the selected product.
     * @return Product - The Product object if the quantity is decreased, or null if not.
     */
    public ProductList decreaseProductQuantity(int position) {
        ProductList product = mProducts.get(position);
        int quantity = product.getBalance();
        if (quantity > 0) {
            product.setBalance(quantity - 1);
            notifyItemChanged(position);
            return product;
        }
        return null;
    }

    /**
     * Method that gets invoked when the user presses the 'Delete' button on the popup menu.
     * This deletes the selected product from the ArrayList and from the UI.
     * @param position - The ArrayList position of the selected product.
     */
    public void deleteProduct(int position) {
        mProducts.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ProductList product = mProducts.get(position);

        Log.d(TAG,"Adapter product item id : "+product.getId());
        holder.mProductNameTextView.setText(product.getName());


        // Product price needs to be rounded to nearest 2 decimal places to avoid super long price.
        double roundedPrice = Math.round(product.getPrice() * 100.0) / 100.0;
        holder.mProductPriceTextView.setText(String.format(mContext.getString(R.string.string_format_product_price), String.valueOf(roundedPrice)));
        holder.mProductQuantityTextView.setText(String.format(mContext.getString(R.string.string_format_product_quantity), String.valueOf(product.getBalance()))+" "+product.getUnit());
        // Set an OnClickListener to the overflow button which will inflate a popup menu that allows
        // user to track a sale or delete the selected product.
        holder.mOverflowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(mContext, view);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int position = holder.getAdapterPosition();
                        switch (item.getItemId()) {
                            case R.id.action_sale:
                                // Track a sale for this product. Let MainActivity do this job.
                                mOnItemSaleListener.onItemSale(position);
                                return true;
                            case R.id.action_delete:
                                // Delete this product. Let MainActivity do this job.
                                mOnItemDeleteListener.onItemDelete(product, position);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.inflate(R.menu.menu_popup);
                popupMenu.show();
            }
        });
        // Set an OnClickListener to the root view which will dispatch OnItemClickListener to
        // MainActivity when it is pressed by the user. This will navigate to DetailActivity.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG,"item count = "+mProducts.size());
        return mProducts.size();
    }

    public interface OnItemClickListener {
        void onItemClick(ProductList product);
    }

    public interface OnItemSaleListener {
        void onItemSale(int position);
    }

    public interface OnItemDeleteListener {
        void onItemDelete(ProductList product, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mProductNameTextView;
        private TextView mProductPriceTextView;
        private TextView mProductQuantityTextView;
        private ImageButton mOverflowButton;

        public ViewHolder(View itemView) {
            super(itemView);
            mProductNameTextView = (TextView) itemView.findViewById(R.id.product_name_text_view);
            mProductPriceTextView = (TextView) itemView.findViewById(R.id.product_price_text_view);
            mProductQuantityTextView = (TextView) itemView.findViewById(R.id.product_quantity_text_view);
            mOverflowButton = (ImageButton) itemView.findViewById(R.id.overflow_button);
        }

    }

}