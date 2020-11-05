import 'package:flutter/material.dart';

void main() => runApp(new ListApp());

class ListApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'List App',
      theme: new ThemeData(primarySwatch: Colors.blue),
      home: new ListAppPage(),
    );
  }
}

class ListAppPage extends StatefulWidget {
  ListAppPage({Key key}) : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return new _ListAppPageState();
  }
}

class _ListAppPageState extends State<ListAppPage> {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text('List App'),
      ),
      body: new ListView(children: _getListData()),
    );
  }
}

_getListData() {
  List<Widget> widgets = [];
  for (int i = 0; i < 100; i++) {
    widgets.add(new Padding(
      padding: new EdgeInsets.all(10.0),
      child: new Text('Row $i'),
    ));
  }
  return widgets;
}