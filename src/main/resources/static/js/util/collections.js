export function getFilteredGames(games, filterTags) {
    console.log(games)
    console.log(filterTags)
    let res = [];
    games.forEach(g => {
        var toAdd = true;
        for (var i = 0; i < filterTags.length; i++) {
            var fName = filterTags[i];
            var containsCurrTag = false;
            g.tags.forEach(gt => {
                if (gt.name === fName) {
                    containsCurrTag = true;
                }
            })
            if (!containsCurrTag) {
                toAdd = false;
                break;
            }
        }
        if (toAdd) res.push(g);
    })
    console.log(res)
    return res
}

export function getNewTags(allTags, chosenTags) {
    var res = []
    chosenTags.forEach(ct => {
        if (!allTags.includes(ct)) {
            res.push(ct)
        }
    })
    return res
}