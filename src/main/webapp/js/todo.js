var content;

$(document).ready(function () {
    content = $("#content");
    load();
});

function initEvents() {
    $(".messages").click(function () {
        var _this = $(this);
        var id = _this.data("id");
        var status = !_this.hasClass("complete");
        $.ajax({
            url: "/todo/changeStatus",
            data: {todoId: id, status: status},
            type: "post"
        }).done(function (res) {
            if (res == "success") {
                status ? _this.addClass("complete") : _this.removeClass("complete");
            }
        });
    });

    $(".remove").click(function() {
        var _this = $(this);
        var id = _this.data("id");
        $.ajax({
            url: "/todo/remove",
            data: {todoId: id},
            type: "post"
        }).done(function (res) {
            if (res == "success") {
                _this.parent().remove();
            }
        });
    });
}

function load() {
    $.post("/todo/getList", function (json) {
        var todoList = createTodoList(json);
        todoList.forEach(function (todo) {
            renderTodo(todo);
        });
        initEvents();
    });

    $("#addToDoButton").click(addTodo);
}

function createTodoList(json) {
    var resultTodoList = [];
    JSON.parse(json).forEach(function (todo) {
        resultTodoList.push(new Todo(todo.id, todo.task, todo.complete))
    });
    return resultTodoList;
}

function renderTodo(todo) {
    var tr = $("<tr/>", {class: "trTable"});
    var tdTask = $("<td/>", {
        class: todo.isComplete() ? "messages complete" : "messages",
        text: todo.getTask(),
        "data-id": todo.getId()
    });
    var tdDelete = $("<td/>", {
        class: "remove",
        text: "delete",
        "data-id": todo.getId()
    });

    tdTask.appendTo(tr);
    tdDelete.appendTo(tr);
    tr.appendTo(content);
}

function addTodo() {
    var _todo = $("#todo");
    var task = _todo.val();
    if ($.trim(task).length > 0) {
        $.ajax({
            type: 'post',
            url: "/todo/add",
            data: {task: task}
        }).done(function (json) {
            var obj = JSON.parse(json);
            renderTodo(new Todo(obj.id, obj.task, obj.complete));
            initEvents();

            _todo.val(""); //clear
        });
    }
}

function Todo(id, task, complete) {
    this.id = id;
    this.task = task;
    this.complete = complete;

    this.getId = function () {
        return id;
    };

    this.getTask = function () {
        return task;
    };

    this.isComplete = function () {
        return complete;
    };
}