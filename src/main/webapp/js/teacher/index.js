var level = 'all';
var objType;

/**
 * 教师的流程和实体都不会太多，所以在教师界面分页交由js来做
 * 新增期刊的分页统一由server做
 **/

//申报流程相关
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
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
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
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
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
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
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
                    }
                    //, {
                    //    field: 'variableMap.View.publisher',
                    //    title: '出版社',
                    //    sortable: true
                    //}
                    , {
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
                flat: true,
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
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
                pagination: true,
                sidePagination: "client",
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
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
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
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
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
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
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
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
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
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
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
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
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: true,
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
                        field: 'variableMap.Main-ActorName',
                        title: '负责人'
                        //,
                        //formatter: 'allMainActorTran'
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
    }
}

$('#newPaper').click(function () {
    workflow.startOrder(userName, "basicProcess_Beta", "paper").success(function (data) {
        if(objType !== 'paper') {
            window.location.href = "/index/process/paper/all";
            afterSuccess("新建成功,正在切换到论文查看");
        }
        viewTable.bootstrapTable("refresh");
    });
});

$('.allSubmit').click(function () {
    BootstrapDialog.confirm({
        title: '统一提交',
        message: '确认 ?',
        type: BootstrapDialog.TYPE_INFO,
        closable: true,
        draggable: true,
        btnCancelLabel: '取消',
        btnOKLabel: '确认',
        btnOKClass: 'btn-ok',
        callback: function (result) {
            /**
             * userName,taskId,status
             */
            if (result) {
                workflow.submitByTeacher({WF_User: userName}).success(function (data) {
                    console.log(data);
                    if (data == 'true') {
                        afterSuccess("提交成功！");
                    } else {
                        BootstrapDialog.alert("提交失败");
                    }
                    viewTable.bootstrapTable('refresh');
                });
            }
        }
    });
});


var processStates = {
    "process": {
        url: "process",
        enter: function () {
            $('#ProcessToolbar').show();
            //todo 处理页面跳转
            viewTable.on('click-row.bs.table', function (e, row) {
                window.location.href = '/order/' + row['id'];
                //console.log(objType);
                //if(objType !== 'paper'){
                //    window.location.href = '/order/' + row['orderId'];
                //}else{ // unComplete/
                //    window.location.href = '/order/' + row['id'];
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
            console.log("enter: " + this.name + "; param: " + option.param['obj']);
            objType = option.param['obj'];

        },
        update: function (option) {
            console.log("update: " + this.name + "; param: " + option.param['obj']);
            if (objType != option.param['obj']) {
                objType = option.param['obj'];
                processView();
            }
        }
    },
    "process.something.all": {
        url: 'all',
        enter: function (option) {
            console.log("enter: " + this.name);
            level = "all";
            processView();
        }
    },
    "process.something.1st": {
        url: '1st',
        enter: function (option) {
            console.log("enter: " + this.name);
            level = "1st";
            processView();
        }
    },
    "process.something.2nd": {
        url: '2nd',
        enter: function (option) {
            console.log("enter: " + this.name);
            level = "2nd";
            processView();
        }
    }
};

// 实体相关
function entityUrl() {
    return "/api/entity/" + userName + "/" + objType + "/" + level + "/noPag";
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
                    }, {
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
                sidePagination: "client",
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
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }]
            });
            break;
        case 'all':
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: entityUrl(),
                toolbar: '#EntityToolbar',
                showColumns: true,
                showRefresh: true,
                search: true,
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [
                    {
                        radio: true,
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
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
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


//期刊相关
$('.newMag').click(function () {
    workflow.startOrder(userName, "newMag").success(function (data) {
        afterSuccess("新建成功！");
        viewTable.bootstrapTable('refresh');
    });
});

var magStates = {
    "mag": {
        url: "mag",
        enter: function () {
            $('#MagToolbar').show();

            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: '/api/workflow/allMagOrder',
                pagination: true,
                sidePagination: "server",
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
                toolbar: '#MagToolbar',
                flat: true,
                columns: [{
                    radio: true,
                    visible:false
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
                }]
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

//任务相关
var taskStates = {
    "task": {
        url: "task",
        enter: function () {
            viewTable.bootstrapTable('destroy').bootstrapTable({
                url: '/api/workflow/' + userName + '/confirmTask',
                pagination: true,
                showRefresh: true,
                sidePagination: "client",
                pageSize: 15,
                pageList: [20, 30, 50, 'ALL'],
                flat: true,
                columns: [{
                    radio: true,
                    visible:false
                }, {
                    field: 'id',
                    title: 'id',
                    visible: false
                }, {
                    field: 'variableMap.WF_Latest.name',
                    title: '科研名称'
                }, {
                    field: 'variableMap.WF_Type',
                    title: '科研类型',
                    formatter: "wfTypeTran"
                }, {
                    field: 'variableMap.WF_Latest.ActorList',
                    title: '人员',
                    formatter: "actorTran"
                }, {
                    field: 'variableMap.Status',
                    title: '状态',
                    formatter: 'statusTranTask'
                }, {
                    field: 'operator',
                    align: 'center',
                    title: '操作',
                    formatter: view
                }]
            });

            viewTable.on('click-row.bs.table', function (e, row) {
                window.location.href = '/task/' + row['id'];
            });

        },
        leave: function () {

        }
    }
};

var stateman = new StateMan();

stateman
    .state(processStates)
    .state(entityStates)
    .state(magStates)
    .state(taskStates)
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

    stateman.go('^.' + cls, {param: {obj: objType}});
});