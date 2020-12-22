export function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}

export function extractNames(list) {
    var res = [];
    allTags.forEach(t => res.push(t.name));
    return res;
}

export function extractTags(list) {
    var res = [];
    for (var i = 0; i < list.length; i++) {
        var tag = {name: list[i]}
        res.push(tag);
    }
    return res;
}