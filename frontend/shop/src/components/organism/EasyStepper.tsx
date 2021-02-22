import {ComponentType, createContext, Dispatch, FC, SetStateAction, useContext, useState} from "react";
import {Button, Step, StepLabel, Stepper} from "@material-ui/core";

export interface StepConfig {
    label: string;
    component: ComponentType;
    buttons?: boolean;
}

type StepState = {
    step: number,
    setStep: Dispatch<SetStateAction<number>>,
}
const StepContext = createContext<StepState>({
    step: 0,
    setStep: () => {
    },
});

export const useNextStep = () => {
    const context = useContext(StepContext);
    if (context === undefined) {
        throw new Error("Not inside StepProvider");
    }
}

export const EasyStepper: FC<{ config: StepConfig[] }> = ({config}) => {
    const [step, setStep] = useState(0);

    const stepConfig = config[step];
    const Component = stepConfig.component;
    const buttons = stepConfig.buttons ?? true;

    return <StepContext.Provider value={{step, setStep}}>
        <Stepper activeStep={step} alternativeLabel>
            {config.map((stepConfig, index) => (
                <Step key={stepConfig.label} onClick={() => setStep(index)}>
                    <StepLabel>{stepConfig.label}</StepLabel>
                </Step>
            ))}
        </Stepper>
        <Component/>

        {buttons ? <>
            <Button disabled={step <= 0} onClick={() => setStep(step - 1)}>Vorige</Button>
            <Button disabled={step >= config.length - 1} onClick={() => setStep(step + 1)}>Volgende</Button>
        </> : null}
    </StepContext.Provider>
}