import React from 'react';
import {EasyStepper, StepConfig} from "../organism/EasyStepper";
import {PaymentOptions} from "./PlaceOrder/PaymentOptions";
import {Overview} from "./PlaceOrder/Overview";
import {Confirmation} from "./PlaceOrder/Confirmation";


const stepConfig: StepConfig[] = [
    {label: "Betalings opties", component: PaymentOptions},
    {label: "Controleer de bestelling", component: Overview, buttons: false},
    {label: "Bestelling geslaagd", component: Confirmation},
];

export const PlaceOrder = () => {
    return <EasyStepper config={stepConfig}/>;
}