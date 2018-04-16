/**
 * Created by xiezongyu on 2018/4/9.
 */

var projectStatusEnum = function () {
};
projectStatusEnum.unknown = -1;
projectStatusEnum.in_approval = 0;
projectStatusEnum.budgeting = 1;
projectStatusEnum.bidding = 2;
projectStatusEnum.pending = 3;
projectStatusEnum.cancel = 4;

$.fn.initProjects = function (url) {
    $this = this;
    $this.empty();
    var html = '<option value="none">未选择</option>';
    $.ajax({
            url: url,
            type: 'POST',
            success: function (data) {
                if (data.errorCode == 0) {
                    for (var i = 0; i < data.data.length; i++) {
                        var project = data.data[i];
                        html += '<option value="' + project.projectNumber + '">' +
                            project.projectNumber +
                            '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + project.projectName + '</option>';
                    }
                    $this.html(html);
                }
            },
            error: function (data) {
                alert('错误');
            }
        }
    );


};

function initAllProjects(url) {

}
