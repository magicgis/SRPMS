var level = 'all';
var objType;


//申报流程相关
function processUrl() {
    return "/api/workflow/order/" + userName + "/" + objType + "/" + level;
}

function processView() {
    switch (objType) {
        case "project":
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                sidePagination: "server",
                toolbar: '#ProcessToolbar',
                columns: [
                    {
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '名称',
                        sortable: true
                    }, {
                        field: 'type',
                        title: '类别',
                        sortable: true
                    }, {
                        field: 'rank',
                        title: '等级',
                        sortable: true
                    }, {
                        field: 'rateUnit',
                        title: '评分来源',
                        sortable: true
                    }, {
                        field: 'realDate',
                        title: '实际结题时间',
                        sortable: true
                    }, {
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }],
                responseHandler: tableTrans
            });
            break;
        case 'patent':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                sidePagination: "server",
                toolbar: '#ProcessToolbar',
                columns: [
                    {
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '专利名称',
                        sortable: true
                    }, {
                        field: 'standard.value',
                        title: '专利类型',
                        sortable: true
                    }, {
                        field: 'patentNo',
                        title: '专利号',
                        sortable: true
                    }, {
                        field: 'apprDate',
                        title: '获批时间',
                        sortable: true
                    }, {
                        field: 'ActorList',
                        title: '参与者',
                        sortable: true,
                        formatter: "actorTran"
                    }, {
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }],
                responseHandler: tableTrans
            });
            break;
        case 'paper':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                sidePagination: "server",
                toolbar: '#ProcessToolbar',
                flat: "true",
                columns: [
                    {
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        visible: false
                    }, {
                        field: 'variableMap.WF_0_Submission.name',
                        title: '论文名称'
                    }, {
                        field: 'variableMap.WF_0_Submission.type',
                        title: '论文类别',
                        formatter: "paperTypeTran"
                    }, {
                        field: 'variableMap.WF_0_Submission.pubDate',
                        title: '发表日期'
                    }, {
                        field: 'variableMap.WF_0_Submission.ActorList',
                        title: '参与者',
                        formatter: "actorTran"
                    }, {
                        field: 'variableMap.Status',
                        title: '状态',
                        formatter: 'statusTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'book':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                sidePagination: "server",
                flat: true,
                toolbar: '#ProcessToolbar',
                columns: [
                    {
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '著作名称',
                        sortable: true
                    }, {
                        field: 'pubType',
                        title: '出版类型',
                        sortable: true,
                        formatter: 'pubTypeTrans'
                    }, {
                        field: 'sumWord',
                        title: '著作总字数',
                        sortable: true,
                        formatter: 'sumWordTran'
                    }, {
                        field: 'isAward',
                        title: '著作获奖情况',
                        sortable: true,
                        formatter: 'bkAwardTran'
                    }, {
                        field: 'argMap.Main-ActorName',
                        title: '负责人',
                        sortable: true
                    }, {
                        field: 'publisher',
                        title: '出版社',
                        sortable: true
                    }, {
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }],
                responseHandler: tableTrans
            });
            break;
        case 'achAppraisal':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                sidePagination: "server",
                toolbar: '#ProcessToolbar',
                columns: [
                    {
                        radio: true
                        //visible:false
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '成果鉴定名称',
                        sortable: true
                    }, {
                        field: 'standard.infoMap.jdprop',
                        title:'鉴定类别'
                    }, {
                        field: 'standard.infoMap.jdtype',
                        title:'鉴定等级'
                    }, {
                        field: 'certifyUnit',
                        title: '鉴定单位',
                        sortable: 'true'
                    }, {
                        field: 'date',
                        title: '鉴定日期',
                        sortable: true
                    }, {
                        field: 'ActorList',
                        title: '参与者',
                        sortable: true,
                        formatter: "actorTran"
                    }, {
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }],
                responseHandler: tableTrans

            });
            break;
        case 'achAward':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                sidePagination: "server",
                flat: true,
                toolbar: '#ProcessToolbar',
                columns: [
                    {
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '成果获奖名称',
                        sortable: true
                    }, {
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'date',
                        title: '获奖时间',
                        sortable: true
                    }, {
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }],
                responseHandler: tableTrans
            });
            break;
        case 'achTran':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                sidePagination: "server",
                toolbar: '#ProcessToolbar',
                columns: [
                    {
                        radio: true
                        //visible:false
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '成果转化名称',
                        sortable: true
                    }, {
                        field: 'tranUnit',
                        title: '转让单位',
                        sortable: 'true'
                    }, {
                        field: 'date',
                        title: '转让日期',
                        sortable: true
                    }, {
                        field: 'ActorList',
                        title: '参与者',
                        sortable: true,
                        formatter: "actorTran"
                    }, {
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }],
                responseHandler: tableTrans

            });
            break;
        case 'newFood':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                sidePagination: "server",
                flat: true,
                toolbar: '#ProcessToolbar',
                columns: [
                    {
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '食品名称',
                        sortable: true
                    }, {
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'newFoodDate',
                        title: '获批时间',
                        sortable: true
                    }, {
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }],
                responseHandler: tableTrans
            });
            break;
        case 'newInstru':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                sidePagination: "server",
                flat: true,
                toolbar: '#ProcessToolbar',
                columns: [
                    {
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '食品名称',
                        sortable: true
                    }, {
                        field: 'newInstruDate',
                        title: '获批时间',
                        sortable: true
                    }, {
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }],
                responseHandler: tableTrans
            });
            break;
        case 'newMedicine':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                sidePagination: "server",
                flat: true,
                toolbar: '#ProcessToolbar',
                columns: [
                    {
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '药品名称',
                        sortable: true
                    }, {
                        field: 'date',
                        title: '获批时间',
                        sortable: true
                    }, {
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }],
                responseHandler: tableTrans
            });
            break;
        case 'newOther':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                sidePagination: "server",
                flat: true,
                toolbar: '#ProcessToolbar',
                columns: [
                    {
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '产品名称',
                        sortable: true
                    }, {
                        field: 'date',
                        title: '获批时间',
                        sortable: true
                    }, {
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }],
                responseHandler: tableTrans
            });
            break;
        case 'all':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                sidePagination: "server",
                toolbar: '#ProcessToolbar',
                columns: [
                    {
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        visible: false
                    }, {
                        field: 'name',
                        title: '科研名称'
                    }, {
                        field: 'WF_Type',
                        title: '科研类型',
                        formatter: "wfTypeTran"
                    }, {
                        field: 'Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'Status',
                        title: '状态',
                        formatter: 'statusTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        width: 75,
                        formatter: view
                    }],
                responseHandler: tableTrans
            });
            break;
    }
}


var processStates = {
    "process": {
        url: "process",
        enter: function () {
            $('#ProcessToolbar').show();
            //todo 处理页面跳转
            viewTable.on('click-row.bs.table', function (e, row) {
                window.location.href = '/order/' + row['id'];
            });
        },
        leave: function () {
            $('#ProcessToolbar').hide();
        }
    },
    "process.something": {
        url: ':obj',
        enter: function (option) {
            console.log("enter: " + this.name + "; param: " + JSON.stringify(option.param));
            objType = option.param['obj'];
            processView();

        },
        update: function (option) {
            console.log("update: " + this.name + "; param: " + JSON.stringify(option.param));
            objType = option.param['obj'];
            processView();
        }
    },
    "process.something.all": {
        url: 'all',
        enter: function (option) {
            level = "all";
        }
    },
    "process.something.1st": {
        url: '1st',
        enter: function (option) {
            level = "1st";
        }
    },
    "process.something.2nd": {
        url: '2nd',
        enter: function (option) {
            level = "2nd";
        }
    }
};

// 实体相关
function entityUrl() {
    return "/api/" + objType + "/all";
}

function entityView() {
    switch (objType) {
        case 'project':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                sidePagination: "server",
                columns: [
                    {
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '名称',
                        sortable: true
                    }, {
                        field: 'projtype',
                        title: '类别',
                        sortable: true,
                        formatter: "typeTran"
                    }, {
                        field: 'projrank',
                        title: '等级',
                        sortable: true,
                        formatter: "rankTran"
                    }, {
                        field: 'projbelong',
                        title: '评分归属',
                        sortable: true,
                        formatter: "projbelong"
                    }, {
                        field: 'realDate',
                        title: '实际结题时间',
                        sortable: true
                    }, {
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }, {
                        field: 'process',
                        title: '流程状态',
                        sortable: true,
                        formatter: 'processTran'
                    }]
            });
            break;
        case 'patent':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '专利名称',
                        sortable: true
                    }, {
                        field: 'state',
                        title: '专利类型',
                        sortable: true
                    }, {
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'process',
                        title: '流程状态',
                        sortable: true,
                        formatter: 'processTran'
                    }]
            });
            break;
        case 'paper': //todo
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                sidePagination: "server",
                columns: [
                    {
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        visible: false
                    }, {
                        field: 'name',
                        title: '论文名称'
                    }, {
                        field: 'type',
                        title: '论文类别',
                        formatter: "paperTypeTran"
                    }, {
                        field: 'pubDate',
                        title: '发表日期'
                    }, {
                        field: 'ActorList',
                        title: '参与者',
                        formatter: "actorTran"
                    }, {
                        field: 'Status',
                        title: '状态',
                        formatter: 'statusTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }],
                responseHandler: tableTrans
            });
            break;
        case 'book':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '著作名称',
                        sortable: true
                    }, {
                        field: 'pubType',
                        title: '出版类型',
                        sortable: true,
                        formatter: 'pubTypeTrans'
                    }, {
                        field: 'sumWord',
                        title: '著作总字数',
                        sortable: true,
                        formatter: 'sumWordTran'
                    }, {
                        field: 'bkReward',
                        title: '著作获奖情况',
                        sortable: true
                    }, {
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'publisher',
                        title: '出版社',
                        sortable: true
                    }, {
                        field: 'process',
                        title: '流程状态',
                        sortable: true,
                        formatter: 'processTran'
                    }]
            });
            break;
        case 'achAppraisal':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '成果鉴定名称',
                        sortable: true
                    }, {
                        field: 'standard.infoMap.jdprop',
                        title:'鉴定类别'
                    }, {
                        field: 'standard.infoMap.jdtype',
                        title:'鉴定等级'
                    }, {
                        field: 'certifyUnit',
                        title: '鉴定单位',
                        sortable: 'true'
                    }, {
                        field: 'date',
                        title: '鉴定日期',
                        sortable: true
                    }, {
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'process',
                        title: '流程状态',
                        sortable: true,
                        formatter: 'processTran'
                    }]
            });
            break;
        case 'achAward':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '成果获奖名称',
                        sortable: true
                    }, {
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'date',
                        title: '获奖时间',
                        sortable: true
                    }, {
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }, {
                        field: 'process',
                        title: '流程状态',
                        sortable: true,
                        formatter: 'processTran'
                    }]
            });
            break;
        case 'achTran':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '成果转化名称',
                        sortable: true
                    }, {
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'process',
                        title: '流程状态',
                        sortable: true,
                        formatter: 'processTran'
                    }]
            });
            break;
        case 'newFood':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                    radio: true
                }, {
                    field: 'id',
                    title: 'id',
                    sortable: true,
                    visible: false
                }, {
                    field: 'name',
                    title: '食品名称',
                    sortable: true
                }, {
                    field: 'argMap.Main-ActorName',
                    title: '负责人'
                }, {
                    field: 'date',
                    title: '获批时间',
                    sortable: true
                }, {
                    field: 'process',
                    title: '流程状态',
                    sortable: true,
                    formatter: 'processTran'
                }]
            });
            break;
        case 'newInstru':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                    radio: true
                }, {
                    field: 'id',
                    title: 'id',
                    sortable: true,
                    visible: false
                }, {
                    field: 'name',
                    title: '器械名称',
                    sortable: true
                }, {
                    field: 'date',
                    title: '获批时间',
                    sortable: true
                }, {
                    field: 'argMap.Main-ActorName',
                    title: '负责人'
                }, {
                    field: 'process',
                    title: '流程状态',
                    sortable: true,
                    formatter: 'processTran'
                }]
            });
            break;
        case 'newMedicine':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                    radio: true
                }, {
                    field: 'id',
                    title: 'id',
                    sortable: true,
                    visible: false
                }, {
                    field: 'name',
                    title: '药品名称',
                    sortable: true
                }, {
                    field: 'date',
                    title: '获批时间',
                    sortable: true
                }, {
                    field: 'argMap.Main-ActorName',
                    title: '负责人'
                }, {
                    field: 'process',
                    title: '流程状态',
                    sortable: true,
                    formatter: 'processTran'
                }]
            });
            break;
        case 'newOther':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                    radio: true
                }, {
                    field: 'id',
                    title: 'id',
                    sortable: true,
                    visible: false
                }, {
                    field: 'name',
                    title: '产品名称',
                    sortable: true
                }, {
                    field: 'date',
                    title: '获批时间',
                    sortable: true
                }, {
                    field: 'argMap.Main-ActorName',
                    title: '负责人'
                }, {
                    field: 'process',
                    title: '流程状态',
                    sortable: true,
                    formatter: 'processTran'
                }]
            });
            break;
        case 'all': //todo
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                sidePagination: "server",
                columns: [
                    {
                    radio: true
                    //visible:false
                }, {
                    field: 'id',
                    title: 'id',
                    visible: false
                }, {
                    field: 'name',
                    title: '科研名称'
                }, {
                    field: 'WF_Type',
                    title: '科研类型',
                    formatter: "wfTypeTran"
                }, {
                    field: 'ActorList',
                    title: '人员',
                    formatter: "actorTran"
                }, {
                    field: 'Status',
                    title: '状态',
                    formatter: 'statusTran'
                }, {
                    field: 'operator',
                    align: 'center',
                    title: '操作',
                    width: 75
                }],
                responseHandler: tableTrans
            });
            break;
    }
}

var entityStates = {
    "entity": {
        url: "entity",
        enter: function () {
            $('#EntityToolbar').show();
            //todo 处理页面跳转
            viewTable.on('click-row.bs.table', function (e, row) {
                window.location.href = '/' + objType + '/' + row['id'];
            });
        },
        leave: function () {
            $('#EntityToolbar').hide();
        }
    },
    "entity.something": {
        url: ':obj',
        enter: function (option) {
            objType = option.param['obj'];
            entityView();
        },
        update: function (option) {
            objType = option.param['obj'];
            entityView();
        }
    },
    "entity.something.all": {
        url: 'all',
        enter: function () {
            level = "all";
        }
    },
    "entity.something.1st": {
        url: '1st',
        enter: function () {
            level = "1st";
        }
    },
    "entity.something.2nd": {
        url: '2nd',
        enter: function () {
            level = "2nd";
        }
    }
};



var magStates = {
    "mag": {
        url: "mag",
        enter: function () {
            $('#MagToolbar').show();

            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: '/api/workflow/allMagOrder',
                sidePagination: "server",
                toolbar: '#MagToolbar',
                columns: [{
                    radio: true
                    //visible:false
                }, {
                    field: 'id',
                    title: 'id',
                    sortable: true,
                    visible: false
                }, {
                    field: 'name',
                    title: '刊物全名',
                    sortable: true
                }, {
                    field: 'issn',
                    title: 'ISSN',
                    sortable: true
                    //formatter: typeTran
                }, {
                    field: 'cn',
                    title: 'CN',
                    sortable: true
                }, {
                    field: 'sub',
                    title: '刊物类别',
                    sortable: true
                }, {
                    field: 'type',
                    title: '收录类型',
                    sortable: true
                }, {
                    field: 'operator',
                    align: 'center',
                    title: '操作',
                    formatter: view
                }],
                responseHandler: tableTranMags
            });

            viewTable.on('click-row.bs.table', function (e, row) {
                window.location.href = '/magazine/' + row["id"];
            });
        },
        leave: function () {
            $('#MagToolbar').hide();
        }
    }
};

var stateman = new StateMan();

stateman
    .state(processStates)
    .state(entityStates)
    .state(magStates)
    .on("notfound", function (path) {
        stateman.go("home", {replace: true});
    })
    .on("end", function () {

    })
    .start({html5: true, "root": "/index"});


//参与类型切换
$('.level').click(function () {
    $('.levelOption').html(this.children[0].text + '<span class="ace-icon fa fa-caret-down icon-on-right"></span>');

    var cls = $(this).attr('class').replace("level", "").trim();

    console.log(stateman.current);
    console.log(cls);

    stateman.go('^.' + cls, {param: {obj: objType}});
});