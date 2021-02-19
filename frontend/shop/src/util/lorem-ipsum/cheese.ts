const phrases = ["everyone loves", "the big cheese", "say cheese", "hard cheese", "cut the cheese", "cheese on toast", "chalk and cheese", "who moved my cheese", "cheesy grin", "cheesy feet", "rubber cheese", "cheese and wine", "cheese and biscuits", "cheeseburger", "cauliflower cheese", "fromage", "croque monsieur", "cheese strings", "cheese slices", "cheese triangles", "macaroni cheese", "smelly cheese", "cheesecake", "cow", "goat", "squirty cheese", "melted cheese", "fondue", "airedale", "babybel", "bavarian bergkase", "blue castello", "bocconcini", "boursin", "brie", "caerphilly", "camembert de normandie", "cheddar", "cottage cheese", "cream cheese", "danish fontina", "dolcelatte", "edam", "emmental", "feta", "fromage frais", "gouda", "halloumi", "jarlsberg", "lancashire", "manchego", "mascarpone", "monterey jack", "mozzarella", "paneer", "parmesan", "pecorino", "pepper jack", "port-salut", "queso", "red leicester", "ricotta", "roquefort", "st. agur blue cheese", "stilton", "stinking bishop", "swiss", "taleggio", "when the cheese comes out everybody's happy"];

function capitalize(c: string) {
    return c.charAt(0).toUpperCase() + c.substr(1)
}

const randomPhrase = () => {
    return phrases[Math.floor(Math.random() * phrases.length)];
}

export const cheeseIpsum = (nrOfParagraphs: number = 1, typeOfParagraphs: "short" | "medium" | "long" = "short"): string[] => {

    let ln = 0;
    let max = 0;
    let min = 0;

    switch (typeOfParagraphs) {
        case "short":
            min = 15;
            max = 25;
            break;
        case "medium":
            min = 30;
            max = 50;
            break;
        case "long":
            min = 55;
            max = 75;
            break
    }

    const result: string[] = [];
    for (let i = 0; i < nrOfParagraphs; i++) {
        let l = "";
        let j = capitalize(randomPhrase()) + " "

        ln = Math.floor(Math.random() * (max-min) )+min;
        for (let x = 0; x <= ln; x++) {
            if (x % 7 === 1) {
                j += randomPhrase() + ". " + capitalize(randomPhrase()) + " "
            } else {
                j += randomPhrase() + " "
            }
        }
        l += j.trim() + ".";
        result.push(l);
    }

    return result;
};