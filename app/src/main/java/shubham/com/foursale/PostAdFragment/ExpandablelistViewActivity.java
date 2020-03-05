package shubham.com.foursale.PostAdFragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import shubham.com.foursale.R;


public class ExpandablelistViewActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListView expandableListView1;

    String[] parent = new String[]{"Automotive","Property","Services","Buy & Sell","Animals & Pets","Jobs"};
    String[] q1 = new String[]{"Cars","Automotive Services","Bikes","Watercraft","Accessories & Spare Parts","Rentals"};
    String[] q2 = new String[]{"Property For Sale","Property For Rent","International Property","Property For Exchange","House Sharing"
            ,"Property Services"};

    String[] q3 = new String[]{"Pack & Move","General Contracting","Satellite","Parties","Travel & Tourism","Clearing Agent",
            "Health & Lifestyle","Food & Catering","Other Services"};

    String[] q4 = new String[]{"Electronics","Camping Equipments","Furniture","Fashion","Art & Collectibles","Miscellaneous"};
    String[] q5 = new String[]{"Animals","Pets","Birds","Animals & Pets Services","Animals & Pets Equipments","Animals & Pets Food"};
    String[] q6 = new String[]{"Teaching & Training","Employer","Job Seeker"};


    String[] des1 = new String[]{"Asian Cars","American Cars","European Cars","Junk Cars","Classic Cars","Heavy Equipments","Busses For Sale"};
    String[] des2 = new String[]{"Cars Services","Remote Programming","Polishing","Crane","Insurance","Shipment","Learn Drive",
            "Watercraft Services","Mechanic & Electronic Cars"};
    String[] des3 = new String[]{"Motorbikes Sport","Quad Bikes","Bicycles","Wanted Bikes"};
    String[] des4 = new String[]{"Boats","Jet Ski","Required Waterworks"};
    String[] des5 = new String[]{"Car Spare Parts","Car Acessories","Car Tools","Engine & Gears","Rims","Waterworks Equipment",
            "Bikes Accessories & Spare Parts"};
    String[] des6 = new String[]{"Buses","Heavy Equipment Rental","Motor Bike Rental","Taxi"};
    String[] st1= new String[]{"Units For Sale","Shops","Farms For Sale","Land","Company","Private Gardens For Sale","Chalets"
            ,"Wanted Property For Sale"};
    String[] st2= new String[]{"Units For Rent","Shops","Stores","Furnished Apartments For Rent","Private Gardens For Rent","Farms For Rent"
            ,"Offices For Rent","Chalets For Rent","Wanted Property For Rent"};
    String[] st3= new String[]{"Saudi Arabia","Turkey","United Arab Emirates","Egypt","Other Countries"};
    String[] des7 = new String[]{"ResidentialLands","House","Housing Request"};
    String[] des8 = new String[]{""};
    String[] des9 = new String[]{""};
    String[] des10 = new String[]{"Ahmadi District","Kuwait City District","Hawalli District","Farwaniyah District","Jahra District"
            ,"Mubarek Al-Kabeer District"};
    String[] des11 = new String[]{"Plumber","Locksmith","Duct Cleaning","Builders","Central AC Services","Painter","Decoration"
            ,"Gardener","Water Tanks","Metalwork","Aluminum","Bugs Exterminator","Ceramic","Glass","Insulated Roof","Electrician","Carpenter"};
    String[] des12 = new String[]{"Ahmadi District","Farwaniyah District","Hawalli District","Mubarek Al-Kabeer District","Jahra District"
            ,"Kuwati City District"};
    String[] des13 = new String[]{"Occasions","Photography","DJ","Baby Reception"};
    String[] des14 = new String[]{"Hajj And Umrah","Tourist Visa","Hotels"};
    String[] des15 = new String[]{""};
    String[] des16 = new String[]{"Your Health","Gym & Spa","Hairdresser","Tailor"};
    String[] des17 = new String[]{"Kuwait Kitchen","Coffee","Sweets","Arab Kitchen","Honey","Dates","Meat & Poultry","Fish","Other Foods"};
    String[] des18 = new String[]{"Commercail License","Advertisement Services","Transportation & Logistics","Other Services"};
    String[] des19 = new String[]{"Phone & Tablets","Computers & Networking","Audio","Smart TV","Smart Watches","Camera","Home/Office Appliance"
            ,"Satellite Receiver","Wanted Electronic Devices","Electronic Games","Other Electronics","Home Appliance Maintenance"};
    String[] des20 = new String[]{"Camping Stuff","Tents","Hunting Equipment","Generators","Trampoline For Rent","Caravans",
            "Solar Power","Camps For Rent","Coals","Satellite - Camping"};
    String[] des21 = new String[]{"Wanted Furniture","Home Furnishings","Office Furniture","Upholstery","Textiles"};
    String[] des22 = new String[]{"Ladies","Men","Children"};
    String[] des23 = new String[]{"Antiques","Paintings"};
    String[] des24 = new String[]{"Audio & Musical","Books","Medical Devices","Children's Needs","Gifts","Sports Equipments",
            "Incense","Agriculture Products","Building Materials","Tickets","Wholesale","Stickers","Lost & Found","Other Miscellaneous"};
    String[] des25 = new String[]{"Sheep","Horses","Camels","Other Animals & Pets"};
    String[] des26 = new String[]{"Dogs","Cats"};
    String[] des27 = new String[]{"Chickens","Falcons","Doves","Parrots","Canaries","Other Birds"};
    String[] des28 = new String[]{"Sheep","Horse","Dogs"};
    String[] des29 = new String[]{""};
    String[] des30 = new String[]{""};
    String[] des31 = new String[]{"Languages","All Science","Math Teaching","University Services","Other Subjects"};
    String[] des32 = new String[]{"Accounting","Technology & Engineering","Architecture & Manufacturing","Medical","Hospitality & Tourism"
            ,"Law Enforcement","Marketing","Other Jobs"};
    String[] des33 = new String[]{"Accounting & Management","Technology","Architecture & Manufacturing","Medical",
            "Hospitality & Tourism","Law Enforcement","Marketing","Other Jobs"};
    String[] des34 = new String[]{"Nissan","Lexus"};


    LinkedHashMap<String,String[]> thirdLevelq1 = new LinkedHashMap<>();
    LinkedHashMap<String,String[]>  thirdLevelq2 = new LinkedHashMap<>();
    LinkedHashMap<String,String[]>  thirdLevelq3 = new LinkedHashMap<>();
    LinkedHashMap<String,String[]>  thirdLevelq4 = new LinkedHashMap<>();
    LinkedHashMap<String,String[]>  thirdLevelq5 = new LinkedHashMap<>();
    LinkedHashMap<String,String[]>  thirdLevelq6 = new LinkedHashMap<>();

    LinkedHashMap fourLevelq1 = new LinkedHashMap<>();

    //Second Level array list
    List<String[]> secondLevel = new ArrayList<>();

    //Inner level data
    List<LinkedHashMap<String,String[]>> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandablelist_view);
        setUpAdapter();

        findview();
    }


    public void setUpAdapter()
    {
        secondLevel.add(q1);
        secondLevel.add(q2);
        secondLevel.add(q3);
        secondLevel.add(q4);
        secondLevel.add(q5);
        secondLevel.add(q6);

        thirdLevelq1.put(q1[0], des1);
        thirdLevelq1.put(q1[1], des2);
        thirdLevelq1.put(q1[2], des3);
        thirdLevelq1.put(q1[3], des4);
        thirdLevelq1.put(q1[4], des5);
        thirdLevelq1.put(q1[5], des6);

        thirdLevelq2.put(q2[0], st1);
        thirdLevelq2.put(q2[1], st2);
        thirdLevelq2.put(q2[2], st3);
        thirdLevelq2.put(q2[3], des7);
        thirdLevelq2.put(q2[4], des8);
        thirdLevelq2.put(q2[5], des9);

        thirdLevelq3.put(q3[0], des10);
        thirdLevelq3.put(q3[1], des11);
        thirdLevelq3.put(q3[2], des12);
        thirdLevelq3.put(q3[3], des13);
        thirdLevelq3.put(q3[4], des14);
        thirdLevelq3.put(q3[5], des15);
        thirdLevelq3.put(q3[6],des16);
        thirdLevelq3.put(q3[7],des17);
        thirdLevelq3.put(q3[8],des18);

        thirdLevelq4.put(q4[0], des19);
        thirdLevelq4.put(q4[1], des20);
        thirdLevelq4.put(q4[2], des21);
        thirdLevelq4.put(q4[3], des22);
        thirdLevelq4.put(q4[4], des23);
        thirdLevelq4.put(q4[5], des24);

        thirdLevelq5.put(q5[0], des25);
        thirdLevelq5.put(q5[1], des26);
        thirdLevelq5.put(q5[2], des27);
        thirdLevelq5.put(q5[3], des28);
        thirdLevelq5.put(q5[4], des29);
        thirdLevelq5.put(q5[5], des30);

        thirdLevelq6.put(q6[0], des31);
        thirdLevelq6.put(q6[1], des32);
        thirdLevelq6.put(q6[2], des33);

        //fourLevelq1.put(thirdLevelq1,des34);

        data.add(thirdLevelq1);
        data.add(thirdLevelq2);
        data.add(thirdLevelq3);
        data.add(thirdLevelq4);
        data.add(thirdLevelq5);
        data.add(thirdLevelq6);

        //data.add(fourLevelq1);

        expandableListView = (ExpandableListView) findViewById(R.id.expandible_listview);

        //passing three level of information to constructor
        ThreeLevelListAdapter threeLevelListAdapterAdapter = new ThreeLevelListAdapter(this, parent, secondLevel, data);

        expandableListView.setAdapter(threeLevelListAdapterAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

    }
    private void findview()
    {

    }

}
