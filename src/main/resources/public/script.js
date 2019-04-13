var xhr = new XMLHttpRequest();

function post(method, path, body, callback) {
    if (!xhr) { return; }
    xhr.open(method, path, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            callback(xhr);
        }
    };
    xhr.send(body);
}

function deleteToDo(id) {
    var form = new FormData().append("id", id);
    post('DELETE', "/delete", form, empty);
    var todo = document.getElementById(id);
    if (!todo.classList.contains("__done")) {
        itemLeft(-1);
    }
    todo.parentElement.removeChild(todo);
}

function createToDo() {
    var description = document.querySelector('.todos-creator_text-input').value;
    if (description.trim() === "") { return; }
    var form = new FormData();
    form.append("description", description);
    itemLeft(1);
    post('POST', "/create", form, createItem);
}

function createItem(xhr) {
    var responseData = eval("(" + xhr.responseText + ")");
    var description = responseData.description;
    var id = responseData.id;

    var item = document.createElement('div');
    item.classList.add("todos-list_item");
    item.setAttribute('id', id);
    item.appendChild(new function() {
        var checkBox = document.createElement('div');
        checkBox.classList.add("custom-checkbox");
        checkBox.classList.add("todos-list_item_ready-marker");
        var checkInput = document.createElement('input');
        checkInput.type = "checkbox";
        checkInput.classList.add("custom-checkbox_target");
        checkInput.setAttribute('aria-label', 'Mark todo as ready');
        var checkBoxVisual = document.createElement('div');
        checkBoxVisual.classList.add("custom-checkbox_visual");
        var checkBoxVisualIcon = document.createElement('div');
        checkBoxVisualIcon.classList.add("custom-checkbox_visual_icon");
        checkBoxVisual.appendChild(checkBoxVisualIcon);
        checkBox.appendChild(checkInput);
        checkBox.appendChild(checkBoxVisual);
        checkBox.onclick = checkItemEvent;
        return checkBox;
    });
    item.appendChild(new function () {
        var removeButton = document.createElement('button');
        removeButton.classList.add("todos-list_item_remove");
        removeButton.setAttribute('aria-label', 'Delete todo');
        removeButton.onclick = deleteToDoEvent;
        return removeButton;
    });
    item.appendChild(new function () {
        var itemText = document.createElement('div');
        itemText.classList.add("todos-list_item_text-w");
        var text = document.createElement('text');
        text.classList.add("todos-list_item_text");
        text.innerHTML = description;
        itemText.appendChild(text);
        return itemText;
    });
    var todosList = document.getElementsByClassName('todos-list')[0];
    todosList.appendChild(item);
}

function deleteToDoEvent(mouseEvent) {
     var id = mouseEvent.target.parentElement.id;
     deleteToDo(id);
}

function empty() {}

function checkItemEvent(mouseEvent) {
     var itemToCheck = mouseEvent.target.parentElement;
     checkItem(itemToCheck);
}

function checkItem(item) {
    var checked = false;
    var form = new FormData();
    if (!item.classList.contains("__done")) {
       item.classList.add("__done");
       checked = true;
       itemLeft(-1);
    } else {
       item.classList.remove("__done");
       checked = false;
       itemLeft(1);
    }
    form.append("id", item.getAttribute('id'));
    form.append("description", null);
    form.append("checked", checked);
    item.getElementsByClassName("custom-checkbox_target")[0].checked = checked;
    post('PUT', "/update", form, empty);
}

var newCount = document.getElementsByClassName('todos-list_item').length - document.getElementsByClassName('__done').length;

function itemLeft(count) {
    var counter = document.querySelector('.todos-toolbar_unready-counter');
    newCount += count;
    counter.innerHTML = newCount + ' items left';
}

