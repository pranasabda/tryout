package com.example.prana.appkontak;

import android.support.v7.app.AppCompatActivity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;

//ListActivity
//public class MainActivity extends AppCompatActivity

public class MainActivity extends ListActivity {

    //Aray list akan di simpan di dalam searchResults
    ArrayList<HashMap<String, Object>> searchResults;

    ArrayList<HashMap<String, Object>> originalValues;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText kotakpencari=(EditText) findViewById(R.id.kotakpencari);
        ListView playersListView=(ListView) findViewById(android.R.id.list);

        //mengambil LayoutInflater untuk inflating thcustomView
        //disini akan menggunakan custom adapter
        inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //disini data aray akan di deklarasikan
        //dan akan disimpan ke dalam Arraylist
        //tipe data string untuk textview integer untuk gambar icon

        String nama_lengkaps[]={"James Bond","Eggsy","Sherlock Holmes","Johnny English"};
        String oraganisasis[]={"British Secret Service","Kingsman Secret Agency","Freelance investigator","British-American spy"};
        String nomor_tlps[]={"007","811","711","511"};
        String alamats[] ={"KebonGedang Street","Caheum Street","Bakerr Street","Cicadas Street" };
        Integer[] icons ={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};

        originalValues=new ArrayList<HashMap<String,Object>>();

        //hasmap akan menyimpan data sementara dalam listview
        HashMap<String , Object> temp;

        //jumlah baris dalam ListView
        int noOfPlayers=nama_lengkaps.length;

        //pengulangan dalam Arraylist
        for(int i=0;i<noOfPlayers;i++)
        {
            temp=new HashMap<String, Object>();

            temp.put("nama_lengkap", nama_lengkaps[i]);
            temp.put("organisasi", oraganisasis[i]);
            temp.put("icon", icons[i]);

            //menambah kan baris ke dalam  ArrayList
            originalValues.add(temp);
        }
        //searchResults sama dengan OriginalValues
        searchResults=new ArrayList<HashMap<String,Object>>(originalValues);

        //membuat adapter
        //first param-the context
        //second param-the id of the layout file
        //you will be using to fill a row
        //third param-the set of values that
        //will populate the ListView

        final CustomAdapter adapter=new CustomAdapter(this, R.layout.daftar_kontak,searchResults);

        //menset adapter di dalam listview
        playersListView.setAdapter(adapter);

        kotakpencari.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //mengambil text di dalam  EditText
                String searchString=kotakpencari.getText().toString();
                int textLength=searchString.length();
                searchResults.clear();

                for(int i=0;i<originalValues.size();i++)
                {
                    String playerName=originalValues.get(i).get("nama_lengkap").toString();
                    if(textLength<=playerName.length()){

                        //membandingkan data String didalam EditText dengan nama_lengkap  di dalam ArrayList
                        if(searchString.equalsIgnoreCase(playerName.substring(0,textLength)))
                            searchResults.add(originalValues.get(i));
                         }}
                    adapter.notifyDataSetChanged();
                 }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {}

            public void afterTextChanged(Editable s) {}
        });
    }

    //mendefinisikan custom adapter
    private class CustomAdapter extends ArrayAdapter<HashMap<String, Object>>
    {
        public CustomAdapter(Context context, int textViewResourceId,
                             ArrayList<HashMap<String, Object>> Strings) {


            super(context, textViewResourceId, Strings);
        }

        //class untuk menyimpan baris konten (cacheview) di listview
        private class ViewHolder
        {
            ImageView icon;
            TextView nama_lengkap,organisasi;
        }

        ViewHolder viewHolder;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null)
            {
                convertView=inflater.inflate(R.layout.daftar_kontak, null);
                viewHolder=new ViewHolder();

                //isi konten (cache the views)

                viewHolder.icon=(ImageView) convertView.findViewById(R.id.icon);
                viewHolder.nama_lengkap=(TextView) convertView.findViewById(R.id.nama_lengkap);
                viewHolder.organisasi=(TextView) convertView.findViewById(R.id.organisasi);

                //menghubungkan cached views ke dalam convertview

                convertView.setTag(viewHolder);
            }
            else
                viewHolder=(ViewHolder) convertView.getTag();
            int iconId=(Integer) searchResults.get(position).get("icon");

            //menset data untuk ditampilkan
            //viewHolder.icon.setImageDrawable(getResources().getDrawable(iconId));
            viewHolder.nama_lengkap.setText(searchResults.get(position).get("nama_lengkap").toString());
            viewHolder.organisasi.setText(searchResults.get(position).get("organisasi").toString());

            //mengembalikan view untuk ditampilkan
            return convertView;
        }
    }

    /**
     String nama_lengkaps[]={"James Bond","Eggsy","Sherlock Holmes","Johnny English"};
     String oraganisasis[]={"British Secret Service","Kingsman Secret Agency","Freelance investigator","British-American spy"};
     String nomor_tlps[]={"007","811","711","511"};
     String alamats[] ={"KebonGedang Street","Caheum Street","Bakerr Street","Cicadas Street" };

     */

    // Klik Klik Klik
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub

        super.onListItemClick(l, v, position, id);//menggunakan method onlistitemclick dan mencarinya
        //berdasarkan posisi

        String str = searchResults.get(position).get("nama_lengkap").toString();
        try{
            if(str=="James Bond"){
                Toast.makeText(getApplicationContext(), "James Bond | British Secret Service| 007 | KebonGedang Street  ", Toast.LENGTH_SHORT).show();
                //menampilkan pesan text toast saat nama barang diklik kalian bisa mengganti intent di baris ini
            }
            if(str=="Eggsy"){
                Toast.makeText(getApplicationContext(), "Eggsy | Kingsman Secret Agency | 811 | Caheum Street ", Toast.LENGTH_SHORT).show();
            }
            if(str=="Sherlock Holmes"){
                Toast.makeText(getApplicationContext(), "Sherlock Holmes | Freelance investigator | 711 | Bakerr Street  ", Toast.LENGTH_SHORT).show();
            }
            if(str=="Johnny English"){
                Toast.makeText(getApplicationContext(), "Johnny English | British-American spy | 511 | Cicadas Street ", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
