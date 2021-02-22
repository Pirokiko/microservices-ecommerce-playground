import {FC, useState} from "react";
import {
    Accordion,
    AccordionDetails,
    AccordionSummary,
    FormControlLabel,
    Input,
    MenuItem,
    Select
} from "@material-ui/core";

enum PaymentProvider {
    iDeal = "iDeal",
    PayPal = "PayPal",
    MasterCard = "MasterCard",
    Visa = "Visa",
}

const Options: FC<{ provider: PaymentProvider }> = ({provider}) => {
    switch (provider) {
        case PaymentProvider.MasterCard:
        case PaymentProvider.Visa:
            return <FormControlLabel control={<Input/>} label={"Card nr"}/>;
        case PaymentProvider.iDeal:
            return <FormControlLabel control={<Select style={{minWidth: 200}}>
                <MenuItem>Ing</MenuItem>
            </Select>} label={"Bank"}/>
        default:
            return null;
    }
}

export const PaymentOptions: FC = () => {
    const [provider, setProvider] = useState<PaymentProvider>();

    return <>
        {Object.values(PaymentProvider).map(paymentProvider => (
            <Accordion expanded={paymentProvider === provider}>
                <AccordionSummary onClick={() => setProvider(paymentProvider)}>{paymentProvider}</AccordionSummary>
                <AccordionDetails>
                    <Options provider={paymentProvider}/>
                </AccordionDetails>
            </Accordion>
        ))}
    </>
}