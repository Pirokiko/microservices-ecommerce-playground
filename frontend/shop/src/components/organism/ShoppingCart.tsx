import {Avatar, AvatarProps, Drawer, DrawerProps, StyledProps, Typography} from "@material-ui/core";
import React, {FC, useState} from "react";
import {ShoppingCartOutlined} from "@material-ui/icons";
import {ProductGridItem} from "../molecule/ProductGridItem";
import {useCart} from "../providers/ShoppingCartProvider";
import {OrderButton} from "./OrderButton";

const ShoppingCartIcon: FC<Partial<StyledProps> & { onClick: AvatarProps['onClick'] }> = ({className, onClick}) => {
    return <Avatar className={className} onClick={onClick}>
        <ShoppingCartOutlined/>
    </Avatar>
}

const ShoppingCartDrawer: FC<DrawerProps & { close: () => void }> = ({children, close, ...drawerProps}) => {
    const items = useCart();
    return <Drawer {...drawerProps} style={{background: 'darkred', maxWidth: 300}}>
        {items.map(item => <React.Fragment key={item.product.id}>
            <Typography variant={"caption"}>{item.quantity} times</Typography>
            <ProductGridItem item={item.product}/>
        </React.Fragment>)}
        <OrderButton onClick={close}/>
    </Drawer>
}


export const ShoppingCart: FC<Partial<StyledProps>> = ({className}) => {
    const [open, setOpen] = useState(false);
    return <>
        <ShoppingCartIcon className={className} onClick={() => setOpen(true)}/>
        <ShoppingCartDrawer open={open} close={() => setOpen(false)} onClose={() => setOpen(false)}/>
    </>
};