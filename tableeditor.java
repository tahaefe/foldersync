tableViewer.getTable().addMouseListener(new MouseAdapter() {
    @Override
    public void mouseDoubleClick(MouseEvent event) {
        Point point = new Point(event.x, event.y);
        TableItem item = tableViewer.getTable().getItem(point);
        if (item != null) {
            for (int columnIndex = 0; columnIndex < tableViewer.getTable().getColumnCount(); columnIndex++) {
                Rectangle bounds = item.getBounds(columnIndex);
                if (bounds.contains(point)) {
                    final int row = tableViewer.getTable().indexOf(item);
                    final int column = columnIndex;
                    
                    // Hücre düzenlemesini başlatmak için bir TableEditor oluşturun
                    TableEditor editor = new TableEditor(tableViewer.getTable());
                    editor.horizontalAlignment = SWT.LEFT;
                    editor.grabHorizontal = true;
                    
                    // Text bileşenini oluşturun ve mevcut hücre değeriyle başlatın
                    final Text text = new Text(tableViewer.getTable(), SWT.NONE);
                    text.setText(item.getText(column));
                    
                    // Text bileşeninin düzenleme tamamlandığında yapılacak işlemleri içeren Listener'ı tanımlayın
                    text.addModifyListener(new ModifyListener() {
                        @Override
                        public void modifyText(ModifyEvent e) {
                            Text text = (Text) editor.getEditor();
                            String modifiedValue = text.getText();
                            
                            // Hücre değerini güncellemek için gerekli işlemleri yapabilirsiniz
                            tableViewer.getTable().getItem(row).setText(column, modifiedValue);
                            
                            // Sunucuya güncellenen değeri göndermek için gerekli işlemleri yapabilirsiniz
                            
                            // Düzenlemeyi tamamlayın ve TableEditor'ı temizleyin
                            editor.setEditor(null);
                            text.dispose();
                        }
                    });
                    
                    // TableEditor'ı ayarlayın ve düzenlemeyi başlatın
                    editor.setEditor(text, item, column);
                    text.setFocus();
                    
                    break;
                }
            }
        }
    }
});
