
//参与类型切换
$('.level').click(function () {
    $('#levelOption').html(this.children[0].text + '<span class="ace-icon fa fa-caret-down icon-on-right"></span>');
    level = this.id;
    allTable.bootstrapTable("refresh", {url: entityApiUrl(entityType)});
    changeUrl();
});

function entityApiUrl(type) {
    entityType = type;
    return "/api/entity/" + userName + "/" + type + "/" + level;
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
                    title: '操作'
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
                },
                    {
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
                flat:true,
                columns: [{
                    radio: true
                }, {
                    field: 'id',
                    title: 'id',
                    sortable: true,
                    visible: false
                }, {
                    field:'name',
                    title:'著作名称',
                    sortable:true
                },{
                    field:'pubType',
                    title:'出版类型',
                    sortable:true,
                    formatter: 'pubTypeTrans'
                },{
                    field:'sumWord',
                    title:'著作总字数',
                    sortable:true
                },{
                    field:'bkReward',
                    title:'著作获奖情况',
                    sortable:true
                },{
                    field: 'argMap.Main-ActorName',
                    title: '负责人'
                },{
                    field:'publisher',
                    title:'出版社',
                    sortable:true
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
                flat:true,
                columns: [{
                    radio: true
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
                    field: 'achType',
                    title: '成果类型',
                    sortable: true,
                    formatter: 'typeTran'
                },{
                    field: 'argMap.Main-ActorName',
                    title: '负责人'
                },{
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
        case 'newFoodEntity':
            allTable.bootstrapTable('destroy').bootstrapTable({
                url: entityApiUrl('newFood'),
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
                    field:'foodName',
                    title:'食品名称',
                    sortable: true
                },{
                    field:'newFoodDate',
                    title:'获批时间',
                    sortable: true
                } ,{
                    field: 'argMap.Main-ActorName',
                    title: '负责人'
                },{
                    field: 'process',
                    title: '流程状态',
                    sortable: true,
                    formatter: 'processTran'
                }]
            });
            break;
        case 'newInstruEntity':
            allTable.bootstrapTable('destroy').bootstrapTable({
                url: entityApiUrl('newInstru'),
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
                    field:'name',
                    title:'食品名称',
                    sortable: true
                },{
                    field:'newInstruDate',
                    title:'获批时间',
                    sortable: true
                } ,{
                    field: 'argMap.Main-ActorName',
                    title: '负责人'
                },{
                    field: 'process',
                    title: '流程状态',
                    sortable: true,
                    formatter: 'processTran'
                }]
            });
            break;
        case 'newMedicineEntity':
            allTable.bootstrapTable('destroy').bootstrapTable({
                url: entityApiUrl('newMedicine'),
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
                    field:'name',
                    title:'药品名称',
                    sortable: true
                },{
                    field:'date',
                    title:'获批时间',
                    sortable: true
                } ,{
                    field: 'argMap.Main-ActorName',
                    title: '负责人'
                },{
                    field: 'process',
                    title: '流程状态',
                    sortable: true,
                    formatter: 'processTran'
                }]
            });
            break;
        case 'newOtherEntity':
            allTable.bootstrapTable('destroy').bootstrapTable({
                url: entityApiUrl('newOther'),
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
                    field:'name',
                    title:'产品名称',
                    sortable: true
                },{
                    field:'date',
                    title:'获批时间',
                    sortable: true
                } ,{
                    field: 'argMap.Main-ActorName',
                    title: '负责人'
                },{
                    field: 'process',
                    title: '流程状态',
                    sortable: true,
                    formatter: 'processTran'
                }]
            });
            break;
        case 'allEntity':
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
                    width: 75,
                    formatter: view
                }],
                responseHandler: tableTrans
            });
            break;

    }
    changeUrl()
}

