function entityApiUrl(type) {
    entityType = type;
    return "/api/" + type + "/all";
}

//todo 切换实体视图
function switchEntityView(viewType) {
    switch (viewType) {
        case 'projectEntity':
            allTable.bootstrapTable('destroy').bootstrapTable({
                url: entityApiUrl('project'),
                sidePagination: "server",
                columns: [{
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
                    //formatter: "typeTran"
                }, {
                    field: 'rank',
                    title: '等级',
                    sortable: true
                    //formatter: "rankTran"
                }, {
                    field: 'rateUnit',
                    title: '评分来源',
                    sortable: true
                    //formatter: "rateUnitTran"
                }, {
                    field: 'real_time',
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
        case 'patentEntity':
            allTable.bootstrapTable('destroy').bootstrapTable({
                url: entityApiUrl('patent'),
                sidePagination: "server",
                flat: true,
                columns: [{
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
        case 'paperEntity': //todo
            allTable.bootstrapTable('destroy').bootstrapTable({
                url: entityApiUrl("paper"),
                sidePagination: "server",
                columns: [{
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
                }],
                responseHandler: tableTrans
            });
            break;
        case 'bookEntity':
            allTable.bootstrapTable('destroy').bootstrapTable({
                url: entityApiUrl('book'),
                sidePagination: "server",
                columns: [{
                    radio:true
                },{
                    field: 'id',
                    title: 'id',
                    sortable: true,
                    visible:false
                },{
                    field:'name',
                    title:'著作名称',
                    sortable:true
                },{
                    field:'pubType',
                    title:'出版类型',
                    sortable:true
                },{
                    field:'sumWord',
                    title:'著作总字数',
                    sortable:true
                },{
                    field:'bkReward',
                    title:'著作获奖情况',
                    sortable:true
                },{
                    field:'S-ACTOR',
                    title:'提交者',
                    sortable:true
                },{
                    field:'publisher',
                    title:'出版社',
                    sortable:true,
                    formatter:"rankTran"
                },{
                    field:'Status',
                    title:'状态',
                    sortable:true,
                    formatter:'statusTran'
                }, {
                    field: 'process',
                    title: '流程状态',
                    sortable: true,
                    formatter: 'processTran'
                }]
            });
            break;
        case 'achAppraisalEntity':
            allTable.bootstrapTable('destroy').bootstrapTable({
                url: entityApiUrl('achAppraisal'),
                sidePagination: "server",
                flat: true,
                columns: [{
                    radio: true
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
                }]
            });
            break;
        case 'achAwardEntity':
            allTable.bootstrapTable('destroy').bootstrapTable({
                url: entityApiUrl('achAward'),
                sidePagination: "server",
                columns: [{
                    radio: true
                }, {
                    field: 'id',
                    title: 'id',
                    sortable: true,
                    visible: false
                }, {
                    field: 'achName',
                    title: '录入人',
                    sortable: true
                }, {
                    field: 'achType',
                    title: '成果名称',
                    sortable: true
                    // formatter:"typeTran"
                }, {
                    field: 'awardName',
                    title: '类型',
                    sortable: 'true'
                }, {
                    field: 'awardRank',
                    title: '获奖时间',
                    sortable: true
                },{
                    field:'Status',
                    title:'状态',
                    sortable:true,
                    formatter:'statusTran'
                }, {
                    field: 'process',
                    title: '流程状态',
                    sortable: true,
                    formatter: 'processTran'
                }]
            });
            break;
        case 'achTranEntity':
            allTable.bootstrapTable('destroy').bootstrapTable({
                url: entityApiUrl('achTran'),
                sidePagination: "server",
                flat: true,
                columns: [{
                    radio: true
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
                }]
            });
            break;
        case 'allEntity': //todo
            allTable.bootstrapTable('destroy').bootstrapTable({
                url: apiUrl('all'),
                sidePagination: "server",
                columns: [{
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
    changeUrl()
}
