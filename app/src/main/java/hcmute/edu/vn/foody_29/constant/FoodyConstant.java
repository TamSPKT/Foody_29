package hcmute.edu.vn.foody_29.constant;

import hcmute.edu.vn.foody_29.R;

public final class FoodyConstant {
    public static final String PREFERENCES = "FoodyPrefs" ;
    public static final String RESTAURANT_ID = "RestaurantId";
    public static final String PRODUCT_ID = "ProductId";
    public static final String USER_ID = "UserId";

    public static int getRestaurantImage(int id) {
        switch (id) {
            case 1:
                return R.drawable.logo_kfc;
            case 2:
                return R.drawable.logo_jollibee;
            case 3:
                return R.drawable.logo_mcdonalds;
            case 4:
                return R.drawable.logo_ministop;
            case 5:
                return R.drawable.logo_gs25;
            default:
                return R.drawable.icon;
        }
    }

    public static int getProductImage(int id) {
        switch (id) {
            case 1:
                return R.drawable.product1_kfc;
            case 2:
                return R.drawable.product2_kfc;
            case 3:
                return R.drawable.product3_kfc;
            case 4:
                return R.drawable.product4_kfc;
            case 5:
                return R.drawable.product5_kfc;
            case 6:
                return R.drawable.product6_jollibee;
            case 7:
                return R.drawable.product7_jollibee;
            case 8:
                return R.drawable.product8_jollibee;
            case 9:
                return R.drawable.product9_jollibee;
            case 10:
                return R.drawable.product10_jollibee;
            case 11:
                return R.drawable.product11_mcdonalds;
            case 12:
                return R.drawable.product12_mcdonalds;
            case 13:
                return R.drawable.product13_mcdonalds;
            case 14:
                return R.drawable.product14_mcdonalds;
            case 15:
                return R.drawable.product15_mcdonalds;
            case 16:
                return R.drawable.product16_ministop;
            case 17:
                return R.drawable.product17_ministop;
            case 18:
                return R.drawable.product18_ministop;
            case 19:
                return R.drawable.product19_ministop;
            case 20:
                return R.drawable.product20_ministop;
            case 21:
                return R.drawable.product21_gs25;
            case 22:
                return R.drawable.product22_gs25;
            case 23:
                return R.drawable.product23_gs25;
            case 24:
                return R.drawable.product24_gs25;
            case 25:
                return R.drawable.product25_gs25;
            default:
                return R.drawable.icon;
        }
    }
}
