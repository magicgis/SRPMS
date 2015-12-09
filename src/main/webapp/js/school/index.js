var level = 'all';
var objType;


$('.enableAll').click(function() {
    $.ajax({
        type: 'POST',
        url: '/api/staff/enable/all',
        contentType: 'application/json;charset=UTF-8'
    });
});

/*申报流程相关开始*/
function processUrl() {
    return "/api/workflow/order/" + userName + "/" + objType + "/" + level + "/noPag";
}

function processView() {
    switch (objType) {
        case "project":
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                toolbar: '#ProcessToolbar',
                showColumns: true,
                showRefresh: true,
                //pagination: true,
                sidePagination: "client",
                //pageSize: 15,
                //pageList: [20, 30, 50, 'ALL'],
                flat: "true",
                columns: [
                    {
                        radio: false,
                        visible: false
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'variableMap.name',
                        title: '名称',
                        sortable: true
                    }, {
                        field: 'variableMap.View.standard.infoMap.projtype',
                        title: '类别',
                        sortable: true
                    }, {
                        field: 'variableMap.View.standard.infoMap.projrank',
                        title: '等级',
                        sortable: true
                    }, {
                        field: 'variableMap.View.standard.infoMap.projorig',
                        title: '评分来源',
                        sortable: true
                    }, {
                        field: 'variableMap.View.realDate',
                        title: '实际结题时间',
                        sortable: true
                    }, {
                        field: 'variableMap.Main-ActorName',
                        title: '负责人',
                        sortable: true
                    }, {
                        field: 'variableMap.WF_Col',
                        title: '所属学院'
                    }, {
                        field: 'variableMap.Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'patent':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                toolbar: '#ProcessToolbar',
                showColumns: true,
                showRefresh: true,
                //pagination: true,
                sidePagination: "client",
                //pageSize: 15,
                //pageList: [20, 30, 50, 'ALL'],
                flat: "true",
                columns: [
                    {
                        radio: true,
                        visible: false
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'variableMap.name',
                        title: '专利名称',
                        sortable: true
                    }, {
                        field: 'variableMap.View.standard.infoMap.patenttype',
                        title: '专利类型',
                        sortable: true
                    }, {
                        field: 'variableMap.View.patentNo',
                        title: '专利号',
                        sortable: true
                    }, {
                        field: 'variableMap.View.apprDate',
                        title: '获批时间',
                        sortable: true
                    }, {
                        field: 'variableMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'variableMap.WF_Col',
                        title: '所属学院'
                    }, {
                        field: 'variableMap.Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'paper':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                toolbar: '#ProcessToolbar',
                showColumns: true,
                showRefresh: true,
                //pagination: true,
                sidePagination: "client",
                //pageSize: 15,
                //pageList: [20, 30, 50, 'ALL'],
                flat: "true",
                columns: [
                    {
                        radio: true,
                        visible: false
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
                        field: 'variableMap.WF_Col',
                        title: '所属学院'
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
                toolbar: '#ProcessToolbar',
                showColumns: true,
                showRefresh: true,
                //pagination: true,
                sidePagination: "client",
                //pageSize: 15,
                //pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: true,
                        visible: false
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'variableMap.name',
                        title: '著作名称',
                        sortable: true
                    }, {
                        field: 'variableMap.View.pubType',
                        title: '出版类型',
                        sortable: true
                    }, {
                        field: 'variableMap.View.sumWord',
                        title: '著作总字数',
                        sortable: true,
                        formatter: 'sumWordProcessTran'
                    }, {
                        field: 'variableMap.isAward',
                        title: '著作获奖情况',
                        sortable: true,
                        formatter: 'bkAwardTran'
                    }, {
                        field: 'variableMap.Main-ActorName',
                        title: '负责人',
                        sortable: true
                    }, {
                        field: 'variableMap.WF_Col',
                        title: '所属学院'
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
        case 'achAppraisal':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                toolbar: '#ProcessToolbar',
                showColumns: true,
                showRefresh: true,
                //pagination: true,
                sidePagination: "client",
                //pageSize: 15,
                //pageList: [20, 30, 50, 'ALL'],
                flat: "true",
                columns: [
                    {
                        radio: true,
                        visible: false
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'variableMap.name',
                        title: '成果鉴定名称',
                        sortable: true
                    }, {
                        field: 'variableMap.View.standard.infoMap.jdprop',
                        title: '鉴定类别'
                    }, {
                        field: 'variableMap.View.standard.infoMap.jdtype',
                        title: '鉴定等级'
                    }, {
                        field: 'variableMap.View.certifyUnit',
                        title: '鉴定单位',
                        sortable: 'true'
                    }, {
                        field: 'variableMap.View.date',
                        title: '鉴定日期',
                        sortable: true
                    }, {
                        field: 'variableMap.Main-ActorName',
                        title: '负责人',
                        sortable: true
                    }, {
                        field: 'variableMap.WF_Col',
                        title: '所属学院'
                    }, {
                        field: 'variableMap.Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]

            });
            break;
        case 'achAward':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                toolbar: '#ProcessToolbar',
                showColumns: true,
                showRefresh: true,
                //pagination: true,
                sidePagination: "client",
                //pageSize: 15,
                //pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: true,
                        visible: false
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'variableMap.name',
                        title: '成果获奖名称',
                        sortable: true
                    }, {
                        field: 'variableMap.View.standard.infoMap.awdprop',
                        title: '奖励性质',
                        sortable: true
                    }, {
                        field: 'variableMap.View.standard.infoMap.awdtype',
                        title: '获奖类别',
                        sortable: true
                    }, {
                        field: 'variableMap.View.date',
                        title: '获奖时间',
                        sortable: true
                    }, {
                        field: 'variableMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'variableMap.WF_Col',
                        title: '所属学院'
                    }, {
                        field: 'variableMap.Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'achTran':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                toolbar: '#ProcessToolbar',
                showColumns: true,
                showRefresh: true,
                //pagination: true,
                sidePagination: "client",
                //pageSize: 15,
                //pageList: [20, 30, 50, 'ALL'],
                flat: "true",
                columns: [
                    {
                        radio: true,
                        visible: false
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'variableMap.name',
                        title: '成果转化名称',
                        sortable: true
                    }, {
                        field: 'variableMap.View.tranUnit',
                        title: '转让单位',
                        sortable: 'true'
                    }, {
                        field: 'variableMap.View.date',
                        title: '转让日期',
                        sortable: true
                    }, {
                        field: 'variableMap.Main-ActorName',
                        title: '负责人',
                        sortable: true
                    }, {
                        field: 'variableMap.WF_Col',
                        title: '所属学院'
                    }, {
                        field: 'variableMap.Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]

            });
            break;
        case 'food':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                toolbar: '#ProcessToolbar',
                showColumns: true,
                showRefresh: true,
                //pagination: true,
                sidePagination: "client",
                //pageSize: 15,
                //pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: true,
                        visible: false
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'variableMap.name',
                        title: '食品名称',
                        sortable: true
                    }, {
                        field: 'variableMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'variableMap.WF_Col',
                        title: '所属学院'
                    }, {
                        field: 'variableMap.View.date',
                        title: '获批时间',
                        sortable: true
                    }, {
                        field: 'variableMap.Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'instrument':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                toolbar: '#ProcessToolbar',
                showColumns: true,
                showRefresh: true,
                //pagination: true,
                sidePagination: "client",
                //pageSize: 15,
                //pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: true,
                        visible: false
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'variableMap.name',
                        title: '食品名称',
                        sortable: true
                    }, {
                        field: 'variableMap.View.date',
                        title: '获批时间',
                        sortable: true
                    }, {
                        field: 'variableMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'variableMap.WF_Col',
                        title: '所属学院'
                    }, {
                        field: 'variableMap.Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'medicine':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                toolbar: '#ProcessToolbar',
                showColumns: true,
                showRefresh: true,
                //pagination: true,
                sidePagination: "client",
                //pageSize: 15,
                //pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: true,
                        visible: false
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'variableMap.name',
                        title: '药品名称',
                        sortable: true
                    }, {
                        field: 'variableMap.View.date',
                        title: '获批时间',
                        sortable: true
                    }, {
                        field: 'variableMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'variableMap.WF_Col',
                        title: '所属学院'
                    }, {
                        field: 'variableMap.Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'others':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                toolbar: '#ProcessToolbar',
                showColumns: true,
                showRefresh: true,
                //pagination: true,
                sidePagination: "client",
                //pageSize: 15,
                //pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: true,
                        visible: false
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'variableMap.name',
                        title: '产品名称',
                        sortable: true
                    }, {
                        field: 'variableMap.View.date',
                        title: '获批时间',
                        sortable: true
                    }, {
                        field: 'variableMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'variableMap.WF_Col',
                        title: '所属学院'
                    }, {
                        field: 'variableMap.Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'all':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: processUrl(),
                toolbar: '#ProcessToolbar',
                showColumns: true,
                showRefresh: true,
                //pagination: true,
                sidePagination: "client",
                //pageSize: 15,
                //pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
                    }, {
                        field: 'id',
                        title: 'id',
                        visible: false
                    }, {
                        field: 'variableMap.name',
                        title: '科研名称',
                        formatter: 'processNameTran'
                    }, {
                        field: 'variableMap.WF_Type',
                        title: '科研类型',
                        formatter: "wfTypeTran"
                    }, {
                        field: 'variableMap.WF_Col',
                        title: '所属学院'
                    }, {
                        field: 'variableMap.Status',
                        title: '状态',
                        formatter: 'statusTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        width: 75,
                        formatter: view
                    }]
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
                //if(objType !== 'paper'){
                //    window.location.href = '/order/' + row['orderId'];
                //}else{ // unComplete/
                    window.location.href = '/order/' + row['id'];
                //}
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
/*申报流程相关结束*/

/*实体相关开始*/
function entityUrl() {
    return "/api/" + objType + "/all";
}

function entityView() {
    switch (objType) {
        case 'project':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                toolbar: '#EntityToolbar',
                showColumns: true,
                showRefresh: true,
                search: true,
                pagination: true,
                sidePagination: "server",
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
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
                        field: 'standard.infoMap.projtype',
                        title: '类别',
                        sortable: true
                    }, {
                        field: 'standard.infoMap.projrank',
                        title: '等级',
                        sortable: true
                    }, {
                        field: 'standard.infoMap.projbelong',
                        title: '评分归属',
                        sortable: true
                    }, {
                        field: 'realDate',
                        title: '结题时间',
                        sortable: true
                    }, {
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'dept.value',
                        title: '所属学院'
                    }, {
                        field: 'process',
                        title: '流程状态',
                        sortable: true,
                        formatter: 'processTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'patent':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                toolbar: '#EntityToolbar',
                showColumns: true,
                showRefresh: true,
                search: true,
                pagination: true,
                sidePagination: "server",
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
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
                        field: 'standard.infoMap.patenttype',
                        title: '专利类型',
                        sortable: true
                    }, {
                        field: 'apprDate',
                        title: '获批时间',
                        sortable: true
                    }, {
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'dept.value',
                        title: '所属学院'
                    }, {
                        field: 'process',
                        title: '流程状态',
                        sortable: true,
                        formatter: 'processTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'paper': //todo
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                toolbar: '#EntityToolbar',
                showColumns: true,
                showRefresh: true,
                search: true,
                pagination: true,
                sidePagination: "server",
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
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
                        field: 'dept.value',
                        title: '所属学院'
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
                url: entityUrl(),
                toolbar: '#EntityToolbar',
                showColumns: true,
                showRefresh: true,
                search: true,
                pagination: true,
                sidePagination: "server",
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
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
                        sortable: true
                    }, {
                        field: 'sumWord',
                        title: '总字数',
                        sortable: true,
                        formatter: 'sumWordEntityTran'
                    }, {
                        field: 'isAward',
                        title: '获奖情况',
                        sortable: true,
                        formatter: 'bkAwardEntityTran'
                    }, {
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'dept.value',
                        title: '所属学院'
                    }, {
                        field: 'process',
                        title: '流程状态',
                        sortable: true,
                        formatter: 'processTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'achAppraisal':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                toolbar: '#EntityToolbar',
                showColumns: true,
                showRefresh: true,
                search: true,
                pagination: true,
                sidePagination: "server",
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
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
                        title: '鉴定类别'
                    }, {
                        field: 'standard.infoMap.jdtype',
                        title: '鉴定等级'
                    }
                    //, {
                    //    field: 'certifyUnit',
                    //    title: '鉴定单位',
                    //    sortable: 'true'
                    //}
                    , {
                        field: 'date',
                        title: '鉴定日期',
                        sortable: true
                    }, {
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'dept.value',
                        title: '所属学院'
                    }, {
                        field: 'process',
                        title: '流程状态',
                        sortable: true,
                        formatter: 'processTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'achAward':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                toolbar: '#EntityToolbar',
                showColumns: true,
                showRefresh: true,
                search: true,
                pagination: true,
                sidePagination: "server",
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
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
                        field: 'standard.infoMap.awdprop',
                        title: '奖励性质',
                        sortable: true
                    }, {
                        field: 'standard.infoMap.awdtype',
                        title: '获奖类别',
                        sortable: true
                    }, {
                        field: 'date',
                        title: '获奖时间',
                        sortable: true
                    }, {
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'dept.value',
                        title: '所属学院'
                    }, {
                        field: 'process',
                        title: '流程状态',
                        sortable: true,
                        formatter: 'processTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'achTran':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                toolbar: '#EntityToolbar',
                showColumns: true,
                showRefresh: true,
                search: true,
                pagination: true,
                sidePagination: "server",
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
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
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'dept.value',
                        title: '所属学院'
                    }, {
                        field: 'process',
                        title: '流程状态',
                        sortable: true,
                        formatter: 'processTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'food':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                toolbar: '#EntityToolbar',
                showColumns: true,
                showRefresh: true,
                search: true,
                pagination: true,
                sidePagination: "server",
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
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
                        field: 'dept.value',
                        title: '所属学院'
                    }, {
                        field: 'process',
                        title: '流程状态',
                        sortable: true,
                        formatter: 'processTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'instrument':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                toolbar: '#EntityToolbar',
                showColumns: true,
                showRefresh: true,
                search: true,
                pagination: true,
                sidePagination: "server",
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
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
                        field: 'dept.value',
                        title: '所属学院'
                    }, {
                        field: 'process',
                        title: '流程状态',
                        sortable: true,
                        formatter: 'processTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'medicine':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                toolbar: '#EntityToolbar',
                showColumns: true,
                showRefresh: true,
                search: true,
                pagination: true,
                sidePagination: "server",
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
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
                        field: 'dept.value',
                        title: '所属学院'
                    }, {
                        field: 'process',
                        title: '流程状态',
                        sortable: true,
                        formatter: 'processTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'others':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                toolbar: '#EntityToolbar',
                showColumns: true,
                showRefresh: true,
                search: true,
                pagination: true,
                sidePagination: "server",
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
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
                        field: 'dept.value',
                        title: '所属学院'
                    }, {
                        field: 'process',
                        title: '流程状态',
                        sortable: true,
                        formatter: 'processTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'all': //todo
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                sidePagination: "server",
                showColumns: true,
                showRefresh: true,
                pageList: [20, 30, 50, 'ALL'],
                search: true,
                flat: "true",
                columns: [
                    {
                        radio: false,
                        visible: false
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
                    }]
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
/*实体相关结束*/

/*根据用户搜索开始*/
var viewUser;

function searchUrl() {
    return "/api/entity/" + viewUser + "/" + objType + "/all";
}

function searchView() {
    console.log(objType);
    switch (objType) {
        case 'project':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: searchUrl(),
                sidePagination: "server",
                flat: "true",
                columns: [
                    {
                        radio: false,
                        visible: false
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
                        formatter: "projtypeTran"
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
                    }]
            });
            break;
        case 'patent':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: searchUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
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
                        field: 'standard.infoMap.patenttype',
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
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'paper': //todo
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: searchUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
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
                        formatter: "typeTran"
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
                    }]
            });
            break;
        case 'book':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: searchUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
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
                        sortable: true
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
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }, {
                        field: 'process',
                        title: '流程状态',
                        sortable: true,
                        formatter: 'processTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'achAppraisal':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: searchUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '成果名称',
                        sortable: true
                    }, {
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'process',
                        title: '流程状态',
                        sortable: true,
                        formatter: 'processTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'achAward':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: searchUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '成果名称',
                        sortable: true
                    }, {
                        field: 'standard.infoMap.awdprop',
                        title: '奖励性质',
                        sortable: true
                    }, {
                        field: 'standard.infoMap.awdtype',
                        title: '获奖类别',
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
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'achTran':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: searchUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '成果名称',
                        sortable: true
                    }, {
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'process',
                        title: '流程状态',
                        sortable: true,
                        formatter: 'processTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'food':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: searchUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
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
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'instrument':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: searchUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '医疗器械名称',
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
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'medicine':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: searchUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
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
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'others':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: searchUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
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
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'all':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: searchUrl(),
                sidePagination: "server",
                flat: true,
                columns: [
                    {
                        radio: false,
                        visible: false
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
                        width: 75,
                        formatter: view
                    }]
            });
            break;
    }
}

var searchStates = {
    "search": {
        url: "search",
        enter: function (option) {
            $('#SearchToolbar').show();
            $('#main').removeClass("col-xs-12").addClass("col-xs-9");
            $('#viewUser').selectize({
                valueField: 'id',
                labelField: 'name',
                searchField: 'name',
                create: false,
                maxItems: 1,
                render: {
                    option: function (item, escape) {
                        return '<div>' +
                            '<span class="name">' + escape(item.name) + '</span>' +
                            '&nbsp;' + '&nbsp;' +
                            '<span class="id">' + escape(item.id) + '</span>' +
                            '&nbsp;' + '&nbsp;' +
                            '<span class="dept">' + escape(item["col"].value || '') + '</span>' +
                            '</div>';
                    }
                },
                load: function (query, callback) {
                    //if(!query.length) return callback(student);
                    $.ajax({
                        url: '/api/staff/json',
                        type: 'get',
                        dataType: 'json',
                        data: {
                            query: query
                        },
                        error: function () {
                            callback();
                        },
                        success: function (res) {
                            if (res == undefined || res == null) {
                                return;
                            }
                            callback(res);
                        }
                    });
                }
            });
        },
        leave: function () {
            $('#main').removeClass("col-xs-9").addClass("col-xs-12");
            $('#SearchToolbar').hide();
        }
    },
    "search.username": {
        url: ':username',
        enter: function (option) {
            console.log("enter: " + this.name + "; param: " + JSON.stringify(option.param));
            viewUser = option.param['username'];
        },
        update: function (option) {
            console.log("update: " + this.name + "; param: " + JSON.stringify(option.param));
            viewUser = option.param['username'];
            searchView();
        }
    },
    "search.username.type": {
        url: ':type',
        enter: function (option) {
            console.log("enter: " + this.name + "; param: " + JSON.stringify(option.param));
            objType = option.param['type'];
            searchView();
        },
        update: function (option) {
            console.log("update: " + this.name + "; param: " + JSON.stringify(option.param));
            objType = option.param['type'];
        }
    }

};

/*根据用户搜索结束*/

/*期刊新增开始*/
var magStates = {
    "mag": {
        url: "mag",
        enter: function () {
            $('#MagToolbar').show();

            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: '/api/workflow/allMagOrder',
                sidePagination: "server",
                toolbar: '#MagToolbar',
                flat: "true",
                columns: [{
                    radio: false,
                    visible: false
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
/*期刊新增结束*/

/*用户管理开始*/
var userStates = {
    "user": {
        url: "user",
        enter: function () {
            $('#UserToolbar').show();

            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: '/api/staff/all',
                toolbar: '#UserToolbar',
                flat: true,
                search: true,
                showColumns: true,
                showRefresh: true,
                pagination: true,
                sidePagination: "server",
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
                columns: [
                    {
                        checkbox: true,
                        visible: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '姓名',
                        sortable: true
                    }, {
                        field: 'idCard',
                        title: '身份证号',
                        sortable: true
                    }, {
                        field: 'rank.value',
                        title: '职称',
                        sortable: true
                    }, {
                        field: 'position',
                        title: '岗位',
                        sortable: true
                    }, {
                        field: 'edu',
                        title: '学历',
                        sortable: true
                    }, {
                        field: 'degree',
                        title: '学位',
                        sortable: true
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: disableOrEnable,
                        events: operateEvents
                    }]
            });
            viewTable.on('click-row.bs.table', function (e, row) {
            });
        },
        leave: function () {
            $('#UserToolbar').hide();
        }
    }
};
$('.addUser').click(function () {//scanUser
    window.location.href='/user/new'
});
$('.scanUser').click(function () {
    var $userTable=$('#mainTable');
    var staId=$userTable.bootstrapTable('getSelections')[0]['id'];
   // window.location.href='/user/new/'+staId;
});
$('.resetPwd').click(function(){
    var $userTable=$('#mainTable');
    var staId=$userTable.bootstrapTable('getSelections')[0]['id'];
    if (isNull(staId)) {
        BootstrapDialog.show({
            title: '温馨提示：',
            message: '请选择一条要重置密码的数据！'
        });
        return;
    }
    $.ajax({
        type: 'put',
        url: '../api/staff/reset/' + staId,
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
            successInfo("密码重置成功！");
        },
        error:function(){
            failInfo("密码重置失败!")
        }
    });
});
/*用户管理结束*/
/*基础表管理开始*/
var sysBaseStates = {
    "sysBase": {
        url: "sysBase",
        enter: function () {
            $('#SysBaseToolbar').show();

            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: '/api/baseinfo/all',
                toolbar: '#SysBaseToolbar',
                sidePagination: "server",
                columns: [{
                    radio: true,
                    visible: true
                }, {
                    field: 'id',
                    title: 'id',
                    visible: false
                }, {
                    field: 'tableName',
                    title: '类型',
                    align: 'center',
                    sortable: true
                }, {
                    field: 'value',
                    title: '值',
                    sortable: true
                }]
            });
            //添加
            $("#add").click(function () {
                addItem();
            });
            $("#edit").click(function () {
                editItem();
            });
            $("#delete").click(function () {
                delItem();
            });

        },
        leave: function () {
            $('#SysBaseToolbar').hide();
        }
    }
};
function addItem() {
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_PRIMARY,
        message: function (dialog) {
            var $message = $('<div></div>');
            var pageToLoad = dialog.getData('pageToLoad');
            $message.load(pageToLoad);

            return $message;
        },
        title: "成员信息",
        data: {
            'pageToLoad': '/dialog/baseInfo.html'
        },
        buttons: [{
            id: 'btn-add',
            icon: 'glyphicon glyphicon-check',
            label: '保存(S)',
            cssClass: 'btn-info',
            hotkey: 83,
            autospin: false,
            action: function (dialogRef) {
                if (!isFull()) {
                    BootstrapDialog.show({
                        title: '通知',
                        type: BootstrapDialog.TYPE_INFO,
                        message: '请将信息填写完整。'
                    });
                    return;
                }
                var JsonData = viewTable.serializeJSON();
                saveOrUpdate('/api/baseinfo/new', JSON.stringify(JsonData), 'post').success(function () {
                    $("#baseInfoTable").bootstrapTable('refresh');
                    dialogRef.close();
                });
            }
        }]
    });
}

function editItem() {
    var row = viewTable.bootstrapTable('getSelections');
    if (row.length != 1) {
        BootstrapDialog.show({
            title: '温馨提示：',
            message: '请选择一条要编辑的数据！'
        });
        return;
    }
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_PRIMARY,
        message: function (dialog) {
            var $message = $('<div></div>');
            var pageToLoad = dialog.getData('pageToLoad');
            $message.load(pageToLoad);

            return $message;
        },
        title: "成员信息",
        data: {
            'pageToLoad': '/dialog/baseInfo.html'
        },
        buttons: [{
            id: 'btn-edit',
            icon: 'glyphicon glyphicon-check',
            label: '保存(S)',
            cssClass: 'btn-info',
            hotkey: 83,
            autospin: false,
            action: function (dialogRef) {
                if (!isFull()) {
                    BootstrapDialog.show({
                        title: '通知',
                        type: BootstrapDialog.TYPE_INFO,
                        message: '请将信息填写完整。'
                    });
                    return;
                }
                var JsonData = $("#baseInfo").serializeJSON();
                var url = '/api/baseinfo/' + row.id;
                saveOrUpdate(url, JSON.stringify(JsonData), 'put').success(function () {
                    $("#baseInfoTable").bootstrapTable('refresh');
                    dialogRef.close();
                });
            }
        }],
        onshown: function () {
            $("#baseInfo").autofill(row[0], {
                findbyname: true
            });
        }
    });
}

function delItem() {
    var row = viewTable.bootstrapTable('getSelections');
    if (row.length != 1) {
        BootstrapDialog.show({
            title: '温馨提示：',
            message: '请选择一条要编辑的数据！'
        });
        return;
    }
    $.ajax({
        url: '/api/baseinfo/' + row[0].id,
        type: 'delete',
        success: function () {
            $("#baseInfoTable").bootstrapTable('refresh');
        },
        error: function () {
            BootstrapDialog.show({
                title: '通知',
                type: BootstrapDialog.TYPE_INFO,
                message: '该条数据正被使用，删除失败。'
            });
        }
    });
}

function saveOrUpdate(url, args, method) {
    return $.ajax({
        url: url,
        type: method,
        data: args,
        contentType: 'application/json;charset=UTF-8'
    });
}

/*基础表管理结束*/

var stateman = new StateMan();

stateman
    .state(processStates)
    .state(entityStates)
    .state(magStates)
    .state(searchStates)
    .state(userStates)
    .state(sysBaseStates)
    .on("notfound", function (path) {
        stateman.go("home", {replace: true});
    })
    .on("end", function () {

    })
    .start({html5: true, "root": "/index"});


$('.viewtype').click(function () {
    $('.viewOption').html(this.children[0].text + '<span class="ace-icon fa fa-caret-down icon-on-right"></span>');

    var cls = $(this).attr('class').replace("viewType", "").trim();

    console.log(stateman.current);
    console.log(cls);
    //
    //stateman.go('^.' + cls, {param: {obj: objType}});
});