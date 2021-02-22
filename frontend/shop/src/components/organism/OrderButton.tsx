import React, {FC} from 'react';
import {Button} from "@material-ui/core";
import {Money} from "@material-ui/icons";
import {Page, useChangePage} from "../providers/PageProvider";

export const OrderButton:FC<{onClick?:() => void}> = ({onClick}) => {
    const changePage = useChangePage();
    return <Button onClick={() => {
        onClick && onClick();
        changePage(Page.Order);
    }} startIcon={<Money/>}>
        Bestellen
    </Button>
};