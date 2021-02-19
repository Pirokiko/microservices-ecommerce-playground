import React, {useEffect, useState} from 'react';
import {Listing} from "../template/Listing";
import {Product} from "../../types/entities";
import {ProductGridItem} from "../molecule/ProductGridItem";
// import {makeStyles, Theme} from "@material-ui/core";
//
// const useStyles = makeStyles((theme: Theme) => ({
//     shoppingCart: {
//         position: 'fixed',
//         top: theme.spacing(4),
//         right: theme.spacing(4),
//         zIndex: 10000,
//     }
// }))

const generateName = (ln = 10) => {
    const set = "abcdefghijklmnopqrstuvwxyz";
    let result = "";
    while (result.length < ln) {
        result += set[Math.floor(set.length * Math.random())];
    }
    return result;
}

const generateProduct = (): Product => ({
    id: Math.floor(Math.random() * 1000000),
    name: generateName(Math.random() * 8 + 5),
    cost: Math.random() * 10,
});

const generateProducts = (nr: number): Product[] => {
    return Array.from({length: nr}).map(() => generateProduct());
};


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

export const ProductGrid = () => {
    const [products, loading] = useProducts();
    console.log(products);

    const items = loading ? generateProducts(10) : products;
    const component = loading ? undefined : ProductGridItem;

    return <>
        <Listing items={items} loading={loading} itemComponent={component}/>
    </>
}