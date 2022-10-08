package com.solvd.pageranked.test;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;


public class PRWritable implements Writable{

    private LongWritable pr;
    private List<Text> all_links;

    public PRWritable() {
        super();
        this.pr = new LongWritable();
        this.all_links = new ArrayList<>();
    }

    public PRWritable(LongWritable pr, List<Text> url) {
        super();
        this.pr = pr;
        this.all_links = url;
    }

    public PRWritable(long pr, List<String> urls){
        this.pr = new LongWritable(pr);
        this.all_links = new ArrayList<>();
        for(String url : urls){
            all_links.add(new Text(url));
        }
    }

    @Override
    public void readFields(DataInput arg0) throws IOException {
        pr.readFields(arg0);
        for(Text t: all_links){
            t.readFields(arg0);
        }
    }

    @Override
    public void write(DataOutput arg0) throws IOException {
        pr.write(arg0);
        for(Text t : all_links){
            t.write(arg0);
        }
    }

    public LongWritable getPr() {
        return pr;
    }

    public void setPr(LongWritable pr) {
        this.pr = pr;
    }

    public List<Text> getUrls() {
        return all_links;
    }

    public void setUrls(List<Text> url) {
        this.all_links = url;
    }


}
