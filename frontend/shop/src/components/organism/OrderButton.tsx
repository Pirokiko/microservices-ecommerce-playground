import React from 'react';
import orderApi from "../../api/orderApi";
import {Button} from "@material-ui/core";
import {useCart} from "../providers/ShoppingCartProvider";
import {Money} from "@material-ui/icons";

export const OrderButton = () => {
    const items = useCart();
    return <Button onClick={() => {
        orderApi.order(items);
    }} startIcon={<Money />}>
        Bestellen
    </Button>
};