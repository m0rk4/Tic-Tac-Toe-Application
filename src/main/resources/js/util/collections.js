export function getFilteredGames(games, filterTags) {
    let res = [];
    games.forEach(g => {
        var toAdd = true;
        for (var i = 0; i < filterTags.length; i++) {
            var filterTag = filterTags[i];
            var containsCurrTag = false;
            g.tags.forEach(gt => {
                if (gt.name === filterTag.name) {
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
    return res
}

export function getNewTags(allTags, chosenTags) {
    var res = [], allTs = allTags.map(t => t.name)
    chosenTags.forEach(ct => {
        if (!allTs.includes(ct)) {
            res.push({id: null, name: ct})
        }
    })
    return res
}

var board = null;

function lineWin(a, b, c) {
    console.log(board)
    if (board[a] === ' ') return 'n';
    if (board[a] !== board[b]) return 'n';
    if (board[b] !== board[c]) return 'n';
    return board[a];
}

export function checkWin(boardSerialized) {
    board = boardSerialized
    var wnr;

    if ((wnr = lineWin(1, 2, 3)) !== 'n') return wnr;
    if ((wnr = lineWin(4, 5, 6)) !== 'n') return wnr;
    if ((wnr = lineWin(7, 8, 9)) !== 'n') return wnr;

    // Vertical lines.

    if ((wnr = lineWin(1, 4, 7)) !== 'n') return wnr;
    if ((wnr = lineWin(2, 5, 8)) !== 'n') return wnr;
    if ((wnr = lineWin(3, 6, 9)) !== 'n') return wnr;

    // Diagonal lines.

    if ((wnr = lineWin(1, 5, 9)) !== 'n') return wnr;
    if ((wnr = lineWin(3, 5, 7)) !== 'n') return wnr;

    return 'n';
}