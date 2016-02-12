#include<stdio.h>
#include<iostream>
#include<graphics.h>
#include<conio>
#include<math>
#include<dos>
void main()
{
int gd=DETECT,gm;
initgraph(&gd,&gm,"..\\bgi");
int x = (getmaxy()/2)+10;
for(int i=30;i<= x+100;i++)
{
line(0,getmaxy()/2,(getmaxx()/2)+100,getmaxy()/2);
line((getmaxx()/2)+100,getmaxy()/2,(getmaxx()/2)+250,getmaxy()/2);
line((getmaxx()/2)+250,getmaxy()/2,getmaxx(),getmaxy()/2);
circle(50+i,(getmaxy()/2)-5,5);
circle(90+i,(getmaxy()/2)-5,5);
line(25+i,(getmaxy()/2)-10,115+i,(getmaxy()/2)-10);
line(60+i,(getmaxy()/2)-10,70+i,(getmaxy()/2)-25);
line(80+i,(getmaxy()/2)-10,70+i,(getmaxy()/2)-25);
line(70+i,(getmaxy()/2)-25,70+i,(getmaxy()/2)-65);
circle(70+i,(getmaxy()/2)-77,12);
line(55+i,(getmaxy()/2)-50,70+i,(getmaxy()/2)-55);
line(85+i,(getmaxy()/2)-55,70+i,(getmaxy()/2)-50);
delay(20);
cleardevice();
}
line(0,getmaxy()/2,(getmaxx()/2)+100,getmaxy()/2);
line((getmaxx()/2)+100,getmaxy()/2,(getmaxx()/2)+100,getmaxy()/2+150);
line((getmaxx()/2)+250,getmaxy()/2,getmaxx(),getmaxy()/2);

for(i=30;i<=getmaxy()-250;i++)
{
circle(50+x+150,(getmaxy()/2)-5+i,5);
circle(90+x+150,(getmaxy()/2)-5+i,5);
line(25+x+150,(getmaxy()/2)-10+i,115+x+150,(getmaxy()/2)-10+i);
line(60+x+150,(getmaxy()/2)-10+i,70+x+150,(getmaxy()/2)-25+i);
line(80+x+150,(getmaxy()/2)-10+i,70+x+150,(getmaxy()/2)-25+i);
line(70+x+150,(getmaxy()/2)-25+i,70+x+150,(getmaxy()/2)-65+i);
circle(70+x+150,(getmaxy()/2)-77+i,12);
line(55+x+150,(getmaxy()/2)-50+i,70+x+150,(getmaxy()/2)-55+i);
line(85+x+150,(getmaxy()/2)-55+i,70+x+150,(getmaxy()/2)-50+i);
line(0,getmaxy()/2,(getmaxx()/2)+100,getmaxy()/2);
line((getmaxx()/2)+100,getmaxy()/2,(getmaxx()/2)+100,getmaxy()/2+150);
line((getmaxx()/2)+250,getmaxy()/2,getmaxx(),getmaxy()/2);
delay(15);
cleardevice();
}
line(0,getmaxy()/2,(getmaxx()/2)+100,getmaxy()/2);
line((getmaxx()/2)+100,getmaxy()/2,(getmaxx()/2)+100,getmaxy()/2+150);
line((getmaxx()/2)+250,getmaxy()/2,getmaxx(),getmaxy()/2);
setcolor(0);
line((getmaxx()/2)+100,getmaxy()/2,getmaxx()+250,getmaxy()/2);

circle(50,(getmaxy()/2)-5,5);
circle(90,(getmaxy()/2)-5,5);
line(25,(getmaxy()/2)-10,115,(getmaxy()/2)-10);
line(60,(getmaxy()/2)-10,70,(getmaxy()/2)-25);
line(80,(getmaxy()/2)-10,70,(getmaxy()/2)-25);
line(70,(getmaxy()/2)-25,70,(getmaxy()/2)-65);
circle(70,(getmaxy()/2)-77,12);
line(55,(getmaxy()/2)-50,70,(getmaxy()/2)-55);
line(85,(getmaxy()/2)-55,70,(getmaxy()/2)-50);

getch();
}
