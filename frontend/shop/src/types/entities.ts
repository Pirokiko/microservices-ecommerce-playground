type ID = number;
type UUID = string;

interface BaseEntity {
    id: ID;
}

export interface Product extends BaseEntity {
    name: string;
    cost: number;
}

export interface Order extends BaseEntity {
    orderNumber: UUID;
    customerId: ID;
}

export interface Customer extends BaseEntity {
    addresses: Address[];
}

export interface Address extends BaseEntity {
    street: string;
    houseNumber: number;
    houseNumberAddition: string;
    zipcode: string;
}