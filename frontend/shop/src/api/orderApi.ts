import axios from 'axios';
import {CartItem} from "../types/shoppingcart";

const api = {
    order: (items: CartItem[]) =>
        axios.post('http://localhost:9003/order', {
            customerId: 1,
            items: items.map(item => ({
                productId: item.product.id,
                productCost: item.product.cost,
                quantity: item.quantity
            }))
        }).then(response => response.data)
}

export default api;