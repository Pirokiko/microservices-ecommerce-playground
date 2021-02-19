import React from 'react';
import './App.css';
import {ProductGrid} from "./components/page/ProductGrid";
import {ShoppingCartProvider} from "./components/providers/ShoppingCartProvider";
import {BasePage} from "./components/template/BasePage";

function App() {
    return (
        <ShoppingCartProvider>
            <BasePage>
                <ProductGrid/>
            </BasePage>
        </ShoppingCartProvider>
    );
}

export default App;
