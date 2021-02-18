import React from 'react';
import './App.css';
import {ProductGrid} from "./components/page/ProductGrid";
import {ShoppingCartProvider} from "./components/providers/ShoppingCartProvider";

function App() {
    return (
        <ShoppingCartProvider>
            <ProductGrid/>
        </ShoppingCartProvider>
    );
}

export default App;
