figure("Visible", false);

train = readtable('../../../../../../../../../../../../../../resources/configs/local/models/local/java/programs/configs/java/programs/IndexFiles/real/8698fd37-6152-43d9-90d7-496233f170d1.csv');
times = table2array(train(:,2:2));
times = sort(times);

count = [];
for i=1:length(times)
    count = [count; i];
end

plot(count,times,'k','LineWidth',3);

title('8698fd37-6152-43d9-90d7-496233f170d1', 'Fontsize',80);
xlabel('Configurations');
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
saveas(gcf,'8698fd37-6152-43d9-90d7-496233f170d1',filetype)