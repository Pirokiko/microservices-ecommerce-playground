import {Button, CircularProgress} from "@material-ui/core";
import orderApi from "../../../api/orderApi";
import {useCart} from "../../providers/ShoppingCartProvider";
import {FC, useState} from "react";
import {Order} from "../../../types/entities";
import paymentApi from "../../../api/paymentApi";
import {Page, useChangePage} from "../../providers/PageProvider";
import {Money} from "@material-ui/icons";

export const Overview: FC = () => {
    const items = useCart();
    const [state, setState] = useState<"order" | "payment">("order");
    const [loading, setLoading] = useState(false);
    const changePage = useChangePage();

    return <>
        <div>Overview</div>
        <Button
            startIcon={loading ? <CircularProgress size={"1em"} /> : <Money /> }
            onClick={() => {
                setLoading(true);
                orderApi.order(items)
                    .then((order: Order) => {
                        setState("payment");
                        return paymentApi.create(order);
                    }).then(() => {
                    changePage(Page.OrderFinalized);
                })
                    .finally(() => {
                        setLoading(false);
                    })
            }}>
            {!loading && "Bestellen"}
            {loading && state === "order" && "Vastzetten van uw bestelling"}
            {loading && state === "payment" && "Voorbereiden van uw betaling"}
        </Button>
    </>;
};