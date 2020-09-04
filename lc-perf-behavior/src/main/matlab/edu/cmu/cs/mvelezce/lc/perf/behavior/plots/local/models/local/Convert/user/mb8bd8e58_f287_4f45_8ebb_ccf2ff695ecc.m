figure("Visible", false);

train = readtable('../../../../../../../../../../../../../../resources/configs/local/models/local/java/programs/configs/java/programs/Convert/user/b8bd8e58-f287-4f45-8ebb-ccf2ff695ecc.csv');
times = table2array(train(:,9:9));
times = sort(times);

count = [];
for i=1:length(times)
    count = [count; i];
end

plot(count,times,'Marker','x','MarkerEdgeColor','b','MarkerSize', 8, 'Color','k','LineWidth',2);

title('b8bd8e58-f287-4f45-8ebb-ccf2ff695ecc', 'Fontsize',80);
xlabel(strcat('Configurations (',int2str(length(times)),')'));
ylabel('Performance (s)');

ylim([floor(times(1)) * 1.0, ceil(times(length(times))) * 1.0]);

set(gca,'FontSize',20);
set(gca,'xtick',[])

set(gcf,'Position',[100 100 400 300])

scale=2;
paperunits='centimeters';
filewidth=7.5;%cm
fileheight=5.5;%cm
filetype='pdf';
res=300;%resolution
size=[filewidth fileheight]*scale;
set(gcf,'paperunits',paperunits,'paperposition',[0 0 size]);
set(gcf, 'PaperSize', size);
saveas(gcf,'b8bd8e58-f287-4f45-8ebb-ccf2ff695ecc',filetype)
