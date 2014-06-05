Ext.onReady(function(){
	Ext.create('Ext.form.Panel', {
    title: 'Upload a Photo',
    width: 400,
    bodyPadding: 10,
    frame: true,
    renderTo: Ext.getBody(),
    items: [{
        xtype: 'filefield',
        name: 'file',
        fieldLabel: 'Photo',
        labelWidth: 50,
        msgTarget: 'side',
        allowBlank: false,
        anchor: '100%',
        buttonText: 'Select Photo...'
    },{
		text:'附件业务类型',
		name:'type',
		xtype:'hiddenfield',
		value:'contractAttDir'
	}],
    buttons: [{
        text: 'Upload',
        handler: function() {
            var form = this.up('form').getForm();
            if(form.isValid()){
                form.submit({
                    url: '../common/fileUpload.action',
                    waitMsg: 'Uploading your photo...',
                    success: function(fp, o) {
//                      Ext.Msg.alert('Success', 'Your photo "' + o.result.file + '" has been uploaded.');
                    	MessageUtil.showMessage('Your photo "' + o.result.file + '" has been uploaded.');
                    }
                });
            }
        }
    }]
});
});





