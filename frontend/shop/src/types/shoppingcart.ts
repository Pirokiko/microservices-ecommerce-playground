import {Product} from "./entities";

export interface CartItem {
    product: Product;
    quantity: number;
}