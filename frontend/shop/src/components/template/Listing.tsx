import React, {ComponentType} from 'react';
import {Grid, makeStyles, Theme} from "@material-ui/core";

interface ListingProps<T> {
    items: T[];
    loading: boolean;
    itemComponent?: ComponentType<{ item: T }>
}

const Skeleton = () => <div>EMPTY</div>;

const useStyles = makeStyles((theme: Theme) => ({
    container: {
        padding: theme.spacing(2),
    }
}))

export const Listing = <T, >({items, loading, itemComponent = Skeleton}: ListingProps<T>) => {
    const styles = useStyles();
    const ItemComponent = loading ? Skeleton : itemComponent;
    return <Grid container spacing={2} className={styles.container}>
        {items.map(item => (<Grid key={Math.random()} item xs={12} sm={6} md={4} lg={3} xl={2}>
            <ItemComponent item={item}/>
        </Grid>))}
    </Grid>
}