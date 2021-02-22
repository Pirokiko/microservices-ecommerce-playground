import React from 'react';
import './App.css';
import {ProductGrid} from "./components/page/ProductGrid";
import {ShoppingCartProvider} from "./components/providers/ShoppingCartProvider";
import {BasePage} from "./components/template/BasePage";
import {Page, PageProvider, usePage} from "./components/providers/PageProvider";
import {PlaceOrder} from "./components/page/PlaceOrder";

const Pages = () => {
    const page = usePage();

    switch (page) {
        case Page.OrderFinalized:
            return <div>So long and thanks for all the fish</div>
        case Page.Order:
            return <PlaceOrder/>;
        case Page.ProductListing:
            return <ProductGrid/>;
        default:
            return null;
    }
}

function App() {
    return (
        <PageProvider>
            <ShoppingCartProvider>
                <BasePage>
                    <Pages/>
                </BasePage>
            </ShoppingCartProvider>
        </PageProvider>
    );
}

export default App;
