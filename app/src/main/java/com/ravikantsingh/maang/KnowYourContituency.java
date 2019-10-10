package com.ravikantsingh.maang;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class KnowYourContituency extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    Spinner spin,spin2;
    WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_your_contituency);

        wb=findViewById(R.id.map);
        wb.setWebViewClient(new WebViewClient());

        spin= (Spinner) findViewById(R.id.spinner1);
        spin2= (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter adapter= ArrayAdapter.createFromResource(this,
                R.array.state, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        spin.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(final AdapterView<?> arg0, View arg1, int position, long id) {
        String state[]=null;
        final String text1 = arg0.getItemAtPosition(position).toString();
        if (position == 0) {
            //andra
            state=  new String[]{"araku","srikakulam","vizianagaram","visakhapatnam","anakapalli","kakinada","amalapuram","rajahmundry","narsapuram","eluru","machilipatnam","vijayawada","guntur","narasaraopet","bapatla","ongole","nandyal","kurnool","anantapur","hindupur","kadapa"};

        } else if (position == 1) {
//arunachal pradesh
            state=  new String[]{"arunachal-west","arunachal-east"};
        }
        else if (position == 2) {
            state=  new String[]{"karimganj","silchar","dhubri","kokrajhar","barpeta","gauhati","mangaldoi","tezpur","nowgong","kaliabor","jorhat","dibrugarh","lakhimpur"};
//assam
        } else if (position == 3) {
            state=  new String[]{"valmiki-nagar","paschim-champaran","purvi-champaran","sheohar","sitamarhi","madhubani","jhanjharpur","supaul","patna","araria","kishanganj","katihar","purnia","madhepura","muzaffarpur","vaishali","gopalganj","siwan","maharajganj","saran","hajipur"};
//bihar
        } else if (position == 4) {
//chats
            state=  new String[]{"sarguja","raigarh","janjgir-champa","korba","bilaspur","rajnandgaon","durg","raipur","mahasamund","bastar","kanker"};
        } else if (position == 5) {
//goa
            state = new String[]{"north-goa","south-goa"};
        } else if (position == 6) {
//gujrat
            state = new String[]{"kachchh","banaskantha","patan","mahesana","sabarkantha","gandhinagar","ahmedabad-east","surendranagar","porbandar","jamnagar","amreli","bhavnagar","anand","kheda","panchmahal","dahod","vadodara","chhota-udaipur","bharuch","bardoli","surat","navsari","valsad"};

        } else if (position == 7) {
//harayana
            state = new String[]{"ambala","kurukshetra","sirsa","hisar","karnal","sonipat","rohtak","bhiwani-mahendragarh","gurgaon","faridabad"};

        } else if (position == 8) {
//himacha
            state = new String[]{"kangra","mandi","hamirpur","shimla"};
        }
        else if(position == 9){
//jammu and kashmir
            state = new String[]{"baramulla","srinagar","anantnag","ladakh","udhampur","jammu"};

        } else if (position == 10) {
//jharkhand
            state = new String[]{"rajmahal","dumka","godda","chatra","kodarma","giridih","dhanbad"};


        } else if (position == 11) {
//karnata
            state = new String[]{"chikkodi","belgaum","bagalkot","bijapur","gulbarga","raichur","bidar","koppal"};
        } else if (position == 12) {
//kerela
            state = new String[]{"kasaragod","kannur","vadakara","wayanad","kozhikode"};
        } else if (position == 13) {
            state = new String[]{"morena","bhind","gwalior","guna","sagar","tikamgarh","damoh","khajuraho","satna"};
//mp
        } else if (position == 14) {
//maharastra
            state = new String[]{"nandurbar","dhule","jalgaon","raver","buldhana","akola","amravati"};
        } else if (position == 15) {
//manipur
            state = new String[]{"inner-manipur","outer-manipur"};
        }
        else if (position == 16) {
//meghakau
            state = new String[]{"nagaland"};
        }
        else if (position == 17) {
            //mizoram
            state = new String[]{"shillong","tura-parliament"};

        } else if (position == 18) {

            //nagaland
            state = new String[]{"nagaland"};
        } else if (position == 19) {
            //orisa
            state = new String[]{"bargarh","sambalpur","keonjhar","mayurbhanj"};
        }
        else if (position ==20) {
            //punjab
            state = new String[]{"gurdaspur","amritsar","khadoor-sahib","jalandhar","hoshiarpur","anandpur-sahib"};
        }
        else if (position == 21) {
            //rajasthan
            state = new String[]{"ganganagar","bikaner","jhunjhunu","sikar","jaipur","alwar","karauli-dholpur","dausa","ajmer"};
        }
        else if (position == 22) {
            //sikkhim
            state = new String[]{"sikkim"};
        } else if (position == 23) {
            //tamil nadu
            state = new String[]{"thiruvallur","chennai-north","chennai-south","sriperumbudur","arakkonam"};
        } else if (position == 24) {
            //telangana
            state = new String[]{"adilabad","peddapalle","nizamabad","zahirabad","medak","malkajgiri"};
        } else if (position == 25) {
            //tripura
            state = new String[]{"tripura-west","tripura-east"};
        } else if (position == 26) {
            //uterpradesh
            state = new String[]{"saharanpur","kairana","muzaffarnagar","bijnor","rampur"};
        }
        else if (position == 27) {
//urakhand
            state = new String[]{"tehri-garhwal","garhwal","almora","nainital-udhamsingh","hardwar"};
        }
        else if (position == 28) {
///west bangle

            state = new String[]{"cooch-behar","alipurduars","jalpaiguri","darjeeling","baharampur","basirhat"};

        }
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,state);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin2.setAdapter(aa);
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent1, View view, int position, long id) {

                String text = parent1.getItemAtPosition(position).toString();
                wb.loadUrl("https://www.electionsinindia.com/"+text1+"/"+text+"-parliament-lok-sabha-constituency-elections");

                WebSettings webSettings = wb.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setUseWideViewPort(true);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
