figure("Visible", false);

train = readtable('../../../../../../../../../../../../../../resources/configs/local/models/local/java/programs/configs/java/programs/IndexFiles/real/a1f53969-8bf0-46c8-adab-ca5cfe1f7d8b.csv');
times = table2array(train(:,2:2));
times = sort(times);

count = [];
for i=1:length(times)
    count = [count; i];
end

plot(count,times,'k','LineWidth',3);

title('a1f53969-8bf0-46c8-adab-ca5cfe1f7d8b', 'Fontsize',80);
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
saveas(gcf,'a1f53969-8bf0-46c8-adab-ca5cfe1f7d8b',filetype)
