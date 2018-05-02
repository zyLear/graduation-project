/**
 * Created by xiezongyu on 2018/4/9.
 */

var ProjectStatusEnum = function () {
};
ProjectStatusEnum.unknown = -1;
ProjectStatusEnum.in_approval = 0;
ProjectStatusEnum.budgeting = 1;
ProjectStatusEnum.bidding = 2;
ProjectStatusEnum.pending = 3;
ProjectStatusEnum.cancel = 4;

var BidStatusEnum = function () {
};

BidStatusEnum.unknown = -1;
BidStatusEnum.bided = 0;
BidStatusEnum.winning = 1;
BidStatusEnum.unsuccessful = 2;
BidStatusEnum.contracted = 3;


var ContractStatusEnum = function () {
};

ContractStatusEnum.unknown = -1;
ContractStatusEnum.effective = 0;
ContractStatusEnum.finish = 1;
ContractStatusEnum.cancel = 2;

var BiddingStatusEnum = function () {
};

BiddingStatusEnum.unknown = -1;
BiddingStatusEnum.close = 0;
BiddingStatusEnum.open = 1;
BiddingStatusEnum.finish = 2;


$.fn.initProjects = function (url) {
    var $this = this;
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
                alert('网络错误');
            }
        }
    );
};


$.fn.initBids = function (url) {
    var $this = this;
    $this.empty();
    var html = '<option value="none">未选择</option>';
    $.ajax({
            url: url,
            type: 'POST',
            success: function (data) {
                if (data.errorCode == 0) {
                    for (var i = 0; i < data.data.length; i++) {
                        var bid = data.data[i];
                        html += '<option value="' + bid.bidNumber + '">' +
                            bid.bidNumber +
                            '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + bid.bidCompany + '</option>';
                    }
                    $this.html(html);
                }
            },
            error: function (data) {
                alert('网络错误');
            }
        }
    );
};


$.fn.initContracts = function (url) {
    var $this = this;
    $this.empty();
    var html = '<option value="none">无</option>';
    $.ajax({
            url: url,
            type: 'POST',
            success: function (data) {
                if (data.errorCode == 0) {
                    for (var i = 0; i < data.data.length; i++) {
                        var contract = data.data[i];
                        html += '<option value="' + contract.contractNumber + '">' +
                            contract.contractNumber +
                            '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + contract.contractName + '</option>';
                    }
                    $this.html(html);
                }
            },
            error: function (data) {
                alert('网络错误');
            }
        }
    );
};

function formatBidStatus(value) {
    switch (value) {
        case BidStatusEnum.bided:
            return '投标成功';
        case BidStatusEnum.winning:
            return '中标';
        case BidStatusEnum.unsuccessful:
            return '未中标';
        case BidStatusEnum.contracted:
            return '已签合同';
    }
}

function formatBiddingStatus(value) {
    if (BiddingStatusEnum.close == value) {
        return '未开始';
    } else if (BiddingStatusEnum.open == value) {
        return '招标中';
    } else if (BiddingStatusEnum.finish == value) {
        return '已结束';
    }
}


function formatContractStatus(value) {
    if (ContractStatusEnum.cancel == value) {
        return '已取消';
    } else if (ContractStatusEnum.effective == value) {
        return '进行中';
    } else if (ContractStatusEnum.finish == value) {
        return '已完成';
    }
}


function formatProjectStatus(value) {
    switch (value) {
        case ProjectStatusEnum.in_approval:
            return '审批中';
        case ProjectStatusEnum.budgeting:
            return '预算中';
        case ProjectStatusEnum.bidding:
            return '招标中';
        case ProjectStatusEnum.cancel:
            return '项目取消';
        case ProjectStatusEnum.pending:
            return '待定,项目入库';
    }
}


Date.prototype.format = function (fmt) { //author:  so
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

// return new Date(value).format('yyyy年MM月dd日 hh:mm:ss');

function back() {
    window.history.go(-1);
}

function logout(contextPath) {
    $.get(contextPath + '/user/sure-logout');
    window.location.href = contextPath + '/user/login';
}


function formFileLink(contextPath,filePath,fileName) {
    var url = contextPath+'/downloader/download?filePath='+filePath;
    return '<a href="'+url+'">'+fileName+'</a>';
}

