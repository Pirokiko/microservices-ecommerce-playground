import React, {FC, useContext, useState} from "react";


export enum Page {
    ProductListing,
    Order,
    OrderFinalized
}

type PageState = {
    page: Page,
    setPage: React.Dispatch<React.SetStateAction<Page>>
}

const PageContext = React.createContext<PageState | undefined>(undefined);

export const PageProvider: FC = ({children}) => {
    const [page, setPage] = useState<Page>(Page.ProductListing);
    return <PageContext.Provider value={{page, setPage}}>
        {children}
    </PageContext.Provider>
}

export const usePage = () => {
    const context = useContext(PageContext);
    if (!context) throw new Error("Not inside a PageProvider");

    return context.page;
}

export const useChangePage = () => {
    const context = useContext(PageContext);
    if (!context) throw new Error("Not inside a PageProvider");

    return context.setPage;
}