import SockJS from 'sockjs-client'
import {Stomp} from '@stomp/stompjs'

var stompClient = null;
const handlers = [];
const alertHand = [];

export function connect(id) {
    console.log(id)
    const socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(function () {
        return socket
    });
    stompClient.connect({id: id}, frame => {
        console.log('Connected: ' + frame);
        stompClient.subscribe(`/topic/gamestates/${id}`, game => {
            handlers.forEach(h => h(JSON.parse(game.body)))
        });
        stompClient.subscribe(`/topic/alert/${id}`, data => {
            alertHand.forEach(a => a(JSON.parse(data.body)))
        })
    });
}

export function addHandler(handler) {
    handlers.push(handler)
}

export function alertHandlers(handler) {
    alertHand.push(handler)
}

export function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

export function sendGame(game) {
    stompClient.send(`/app/changeGame/${game.id}`, {}, JSON.stringify(game));
}

export function sendAlert(data) {
    stompClient.send(`/app/showAlert/${data.id}`, {}, JSON.stringify(data));
}