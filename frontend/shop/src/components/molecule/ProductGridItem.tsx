import React, {FC, useMemo} from "react";
import {Product} from "../../types/entities";
import {Button, Card, CardActionArea, CardActions, CardContent, CardMedia, Typography} from "@material-ui/core";
import {ShoppingCart} from "@material-ui/icons";
import {useAddCartItem} from "../providers/ShoppingCartProvider";
import {getImageUrl, getText} from "../../util/placeholder";

export const ProductGridItem: FC<{ item: Product }> = ({item}) => {
    const addCartItem = useAddCartItem();

    const url = useMemo(() => getImageUrl(400, 200), []);
    const text = useMemo(() => getText(), []);

    return (
        <Card>
            <CardActionArea>
                <CardMedia
                    image={url}
                    title="Product name"
                    style={{height: 140}}
                />
                <CardContent>
                    <Typography gutterBottom variant="h5" component="h2">
                        {item.name}
                    </Typography>
                    <Typography variant="body2" color="textSecondary" component="p">
                        {text}
                    </Typography>
                </CardContent>
            </CardActionArea>
            <CardActions>
                <Button size="small" color="primary" startIcon={<ShoppingCart/>} onClick={() => addCartItem(item, 1)}>
                    Add to cart
                </Button>
            </CardActions>
        </Card>
    )
}