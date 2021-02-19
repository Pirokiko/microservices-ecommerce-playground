import {AppBar, Container, Paper, Toolbar, Typography} from "@material-ui/core";
import {FC, ReactNode} from "react";
import {ShoppingBasket} from "@material-ui/icons";
import {ShoppingCart} from "../organism/ShoppingCart";

export const BasePage: FC<{ children: NonNullable<ReactNode> }> = ({children}) => {
    return <Paper>
        <AppBar position="sticky">
            <Toolbar>
                <ShoppingBasket/>
                <Typography variant="h6" color="inherit" noWrap>
                    Micro service shop
                </Typography>

                <ShoppingCart />
            </Toolbar>
        </AppBar>
        <Container maxWidth="lg" children={children}/>
    </Paper>;
};