import axios from 'axios';
import {Order} from "../types/entities";

const api = {
    create: (order: Order) =>
        axios.post('http://localhost:9004/payment', {
            orderId: order.id,
            amount: order.items.reduce((cost, item) => cost + item.productCost * item.quantity, 0),
        })
}

export default api;