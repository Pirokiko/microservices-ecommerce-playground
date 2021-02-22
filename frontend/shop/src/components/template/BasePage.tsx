import {AppBar, Container, makeStyles, Paper, Theme, Toolbar, Typography} from "@material-ui/core";
import {FC, ReactNode} from "react";
import {ShoppingBasket} from "@material-ui/icons";
import {ShoppingCart} from "../organism/ShoppingCart";

const useStyles = makeStyles((theme:Theme) => ({
    cart: {
        position: "absolute",
        right: theme.spacing(2),
    }
}))

export const BasePage: FC<{ children: NonNullable<ReactNode> }> = ({children}) => {
    const styles = useStyles();
    return <Paper>
        <AppBar position="sticky">
            <Toolbar>
                <ShoppingBasket/>
                <Typography variant="h6" color="inherit" noWrap>
                    Micro service shop
                </Typography>

                <ShoppingCart className={styles.cart} />
            </Toolbar>
        </AppBar>
        <Container maxWidth="lg" children={children}/>
    </Paper>;
};