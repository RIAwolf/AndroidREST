package lt.kaunascoding.bootcamptodo.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import lt.kaunascoding.bootcamptodo.R;
import lt.kaunascoding.bootcamptodo.model.DBActions;
import lt.kaunascoding.bootcamptodo.model.DBCommunication;
import lt.kaunascoding.bootcamptodo.model.ItemVO;

/**
 * Created by user on 8/6/2018.
 */

public class ItemVOAdapter extends ArrayAdapter<ItemVO> implements CompoundButton.OnCheckedChangeListener, View.OnClickListener{

    // Model
    private DBCommunication dbActions;

    public ItemVOAdapter(Context context, ArrayList<ItemVO> items, DBCommunication dbActions) {
        super(context, 0, items);
        this.dbActions = dbActions;
    }

    @Override
    public void onClick(View view) {
        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();
        int position = listView.getPositionForView(parentRow);

        ItemVO itemVO = getItem(position);
        dbActions.deleteItem(itemVO);
        ((MainActivity) getContext()).updateView();
    }

    @Override
    public void onCheckedChanged(CompoundButton view, boolean isChecked) {
        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();

        if (listView == null) {
            return;
        }

        int position = listView.getPositionForView(parentRow);

        ItemVO itemVO = getItem(position);

        if(isChecked == true){
            itemVO.done=1;
        }else{
            itemVO.done=0;
        }
        dbActions.updateItem(itemVO);

        ((MainActivity) getContext()).updateView();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemVO item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_item_vo, parent, false);
        }

        CheckBox itemCheckbox = (CheckBox) convertView.findViewById(R.id.item_checkbox);

        itemCheckbox.setText(item.title);
        itemCheckbox.setChecked(item.done == 1);

        itemCheckbox.setOnCheckedChangeListener(this);

        Button deleteButton = (Button) convertView.findViewById(R.id.item_delete);
        deleteButton.setOnClickListener(this);

        return convertView;
    }
}
