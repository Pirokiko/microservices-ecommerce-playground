const placeholderlist = [
    "http://via.placeholder.com/{w}x{h}",
    "https://www.placecage.com/{w}/{h}",
    "http://lorempixel.com/{w}/{h}",
    "https://baconmockup.com/{w}/{h}",
    "https://placekitten.com/{w}/{h}",
    "https://loremflickr.com/{w}/{h}",
    "https://www.fillmurray.com/{w}/{h}",
    "http://placeimg.com/{w}/{h}/any",
    "https://www.stevensegallery.com/{w}/{h}",
]

export const getImageUrl = (width: number, height: number) => {
    const rnd = Math.floor(Math.random() * placeholderlist.length);
    return placeholderlist[rnd]
        .replace('{w}', '' + width)
        .replace('{h}', '' + height)
        .concat("?id=" + Math.random()) // Make each image unique, as much as possible
}