// esse script contém algumas funções úteis html que
// faz com que seja possível auxiliar elementos de um jeito
// que seja compatível com navegadores ie e mozilla

String.prototype.trim = function() {
    var result = this.replace( /^\s+/g, "" ); // leading

    return result.replace( /\s+$/g, "" ); // trailing
}

// retorna o texto desse elemento
function getText(element) {
    text = "";

    if (element.textContent) {
        text = element.textContent;
    } else if (element.innerText) {
        text = element.innerText;
    }

    return text.trim();
}

// determina o texto desse elemento
function setText(element, text) {
    if (element.textContent) {
        element.textContent = text;
    } else if (element.innerText) {
        element.innerText = text;
    }
}

/* queima um evento em um manner de um navegador compatível */
function triggerEvent(element, eventType, canBubble) {
    canBubble = (typeof (canBubble) == undefined) ? true : canBubble;

    if (element.fireEvent) {
        element.fireEvent('on' + eventType);
    } else {
        var evt = document.createEvent('HTMLEvents');

        evt.initEvent(eventType, canBubble, true);

        element.dispatchEvent(evt);
    }
}

function removeLoadListener(element, command) {
    if (window.removeEventListener)
        element.removeEventListener("load", command, true);

    else if (window.detachEvent)
        element.detachEvent("onload", command);
}

function addLoadListener(element, command) {
    if (window.addEventListener)
        element.addEventListener("load", command, true);

    else if (window.attachEvent)
        element.attachEvent("onload", command)
}
