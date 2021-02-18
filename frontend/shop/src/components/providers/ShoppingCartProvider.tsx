import React, {FC, useCallback, useContext, useState} from "react";
import {CartItem} from "../../types/shoppingcart";
import {Product} from "../../types/entities";


interface ShoppingCartState {
    items: CartItem[],
    setItems: React.Dispatch<React.SetStateAction<CartItem[]>>
}

const ShoppingCartContext = React.createContext<ShoppingCartState>({
    // @ts-ignore
    get items() {
        throw new Error('Not within a ShoppingCartProvider');
    },
    setItems: () => {
        throw new Error('Not within a ShoppingCartProvider');
    },
});

export const ShoppingCartProvider: FC = ({children}) => {
    const [items, setItems] = useState<CartItem[]>([]);
    return <ShoppingCartContext.Provider value={{items, setItems}}>
        {children}
    </ShoppingCartContext.Provider>
}

export const useAddCartItem = () => {
    const {items, setItems} = useContext(ShoppingCartContext);

    return useCallback((product: Product, quantity: number) => {
        setItems([...items, {
            product,
            quantity
        }])
    }, [items, setItems]);
}

export const useCart = () => {
    return useContext(ShoppingCartContext).items;
};