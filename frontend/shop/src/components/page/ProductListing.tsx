import React, {FC, useEffect, useState} from 'react';
import {Listing} from "../template/Listing";
import {Card, CardContent, CardHeader, Typography} from "@material-ui/core";

interface Product {
    id: number;
    name: string;
    cost: number;
}

const generateName = (ln = 10) => {
    const set = "abcdefghijklmnopqrstuvwxyz";
    let result = "";
    while(result.length < ln){
        result += set[Math.floor(set.length*Math.random())];
    }
    return result;
}

const generateProduct = (): Product => ({
    id: Math.floor(Math.random() * 1000000),
    name: generateName(Math.random()*8 + 5),
    cost: Math.random() * 10,
});

const generateProducts = (nr: number): Product[] => {
    return Array.from({ length: nr }).map(() => generateProduct());
};

const ProductItem:FC<{ item: Product }> = ({ item }) => (
    <Card>
        <CardHeader title={item.name} />
        <CardContent>
            <Typography>
                ID: {item.id}
            </Typography>
            <Typography>
                &euro; {item.cost}
            </Typography>
        </CardContent>
    </Card>
)

const useProducts = (): [Product[], boolean] => {
    const [items, setItems] = useState<Product[]>([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);

        setTimeout(() => {
            setItems(generateProducts(Math.floor(Math.random() * 7) + 4));
            setLoading(false);
        }, 2500);
    }, []);

    return [items, loading];
};

export const ProductListing = () => {
    const [products, loading] = useProducts();
    console.log(products);

    const items = loading ? generateProducts(10) : products;
    const component = loading ? undefined : ProductItem;

    return <Listing items={items} loading={loading} itemComponent={component} />
}