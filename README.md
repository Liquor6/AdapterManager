# AdapterManager
本项目是一个关于recyclerViewAdapter的万能适配方案
其中包括：
一般recyclerView适配器、带header和footer的recyclerView适配器、同一数据集合下不同item布局显示的recyclerView适配器，不同数据集合下不同item布局显示的recyclerView适配器、
（由于懒惰就不写同一数据集合下不同item布局显示的recyclerView适配器的使用模板了，后期会持续更新跟进修改，如有问题麻烦指正）

------------------------------------------------------以下是相关的使用方法------------------------------------------------------

以下是关于带一般的recyclerViewAdapter使用方式：
private List<A_AddressModel.ResultBean> rightList = new ArrayList<>();
private RecyclerAdapter<A_AddressModel.ResultBean> recyclerAdapterRight;
recyclerAdapterRight = new RecyclerAdapter<>(context, rightList, new RecyclerAdapter.OnItemViewListener<A_AddressModel.ResultBean>() {
           @Override
           public int itemLayout() {
               return R.layout.dialog_selector_address_item;
           }

           @Override
           public void itemView(RecyclerViewHolder holder, int i, final A_AddressModel.ResultBean model) {
               TextView textView = holder.getView(R.id.textView);
           }
       });
       RecyclerViewHelper recyclerViewHelperRight = new RecyclerViewHelper(context, recyclerViewRight);
       recyclerViewHelperRight.setListViewForVertical(recyclerAdapterRight);



以下是关于带header和footer的recyclerViewAdapter使用方式：
     private List<H_MineListModel> dataList = new ArrayList<>();
     private RecyclerHFAdapter<H_MineListModel> recyclerHFAdapter;
     recyclerHFAdapter = new RecyclerHFAdapter<>(oThis, dataList, new RecyclerHFAdapter.OnItemViewListener<H_MineListModel>() {
                @Override
                public int itemLayout() {
                    return R.layout.item_mine_normalview;
                }

                @Override
                public void itemView(RecyclerViewHolder holder, int position, final H_MineListModel model) {
                    TextShowView textShowView_title = holder.getView(R.id.textShowView_title);
                }
            });
            recyclerHFAdapter.addHeaderView(new RecyclerHFAdapter.OnHeaderViewListener() {
                @Override
                public int itemLayout() {
                    return R.layout.item_mine_headerview;
                }

                @Override
                public void itemView(RecyclerViewHolder holder) {
                    ImageView imageView_head = holder.getView(R.id.imageView_head);
                    TextView textView_userStyle = holder.getView(R.id.textView_userStyle);
                    TextView textView_personal_homepage = holder.getView(R.id.textView_personal_homepage);
                }
            });
            recyclerHFAdapter.addFooterView(new RecyclerHFAdapter.OnFooterViewListener() {
                @Override
                public int itemLayout() {
                    return R.layout.item_mine_footerview;
                }

                @Override
                public void itemView(RecyclerViewHolder holder) {
                    ConfirmView confirmView = holder.getView(R.id.confirmView);
                }
            });
            RecyclerViewHelper recyclerViewHelper = new RecyclerViewHelper(oThis, recyclerView);
            recyclerViewHelper.setListViewForVertical(recyclerHFAdapter);

（进阶）以下是关于在不同数据实体情况下展示的recyclerViewAdapter使用方式：
private RecyclerSAdapter recyclerAdapter;
recyclerAdapter = new RecyclerSAdapter(oThis);
recyclerAdapter.addView(new RecyclerSAdapter.OnItemViewListener() {
    @Override
    public int itemLayout() {
        return R.layout.item_home_banner_one;
    }

    @Override
    public int itemType() {
        return 1000;
    }

    @Override
    public void itemView(RecyclerViewHolder holder, int position) {
        Banner banner_one = holder.getView(R.id.banner_one);
    }
});
recyclerAdapter.addView(new RecyclerSAdapter.OnItemViewListener() {
    @Override
    public int itemLayout() {
        return R.layout.item_home_search;
    }

    @Override
    public int itemType() {
        return 2000;
    }

    @Override
    public void itemView(RecyclerViewHolder holder, int position) {
        TextView textView_enterprise = holder.getView(R.id.textView_enterprise);
        TextView textView_personal = holder.getView(R.id.textView_personal);
        SearchView searchView_search = holder.getView(R.id.searchView_search);
    }
});
recyclerAdapter.addView(new RecyclerSAdapter.OnItemViewListener() {
    @Override
    public int itemLayout() {
        return R.layout.item_home_module;
    }

    @Override
    public int itemType() {
        return 3000;
    }

    @Override
    public void itemView(RecyclerViewHolder holder, int position) {
    }
});
recyclerAdapter.addView(new RecyclerSAdapter.OnItemViewListener() {
    @Override
    public int itemLayout() {
        return R.layout.item_home_banner_two;
    }

    @Override
    public int itemType() {
        return 4000;
    }

    @Override
    public void itemView(RecyclerViewHolder holder, int position) {
        Banner banner_two = holder.getView(R.id.banner_two);
    }
});
recyclerAdapter.addView(new RecyclerSAdapter.OnItemViewListener() {
    @Override
    public int itemLayout() {
        return R.layout.item_home_title;
    }

    @Override
    public int itemType() {
        return 5001;
    }

    @Override
    public void itemView(RecyclerViewHolder holder, int position) {
        TextView textView = holder.getView(R.id.textView);
        ImageView imageView = holder.getView(R.id.imageView);
    }
});
recyclerAdapter.addView(a_homeModel.getCreditResult().size(), new RecyclerSAdapter.OnItemViewListener() {
    @Override
    public int itemLayout() {
        return R.layout.item_home_sinepublicity;
    }

    @Override
    public int itemType() {
        return 6000;
    }

    @Override
    public void itemView(RecyclerViewHolder holder, int position) {
        final A_HomeModel.CreditResultBean creditResultBean = a_homeModel.getCreditResult().get(position);
        RelativeLayout relativeLayout = holder.getView(R.id.relativeLayout);
        TextView textView_title = holder.getView(R.id.textView_title);
        TextView textView_time = holder.getView(R.id.textView_time);
    }
});
recyclerAdapter.addView(new RecyclerSAdapter.OnItemViewListener() {
    @Override
    public int itemLayout() {
        return R.layout.item_home_title;
    }

    @Override
    public int itemType() {
        return 5002;
    }

    @Override
    public void itemView(RecyclerViewHolder holder, int position) {
        TextView textView = holder.getView(R.id.textView);
        ImageView imageView = holder.getView(R.id.imageView);
    }
});
recyclerAdapter.addView(a_homeModel.getCreditDynamicResult().size(), new RecyclerSAdapter.OnItemViewListener() {
    @Override
    public int itemLayout() {
        return R.layout.item_home_sinedynamic;
    }

    @Override
    public int itemType() {
        return 7000;
    }

    @Override
    public void itemView(RecyclerViewHolder holder, int position) {
        A_HomeModel.CreditDynamicResultBean creditDynamicResultBean = a_homeModel.getCreditDynamicResult().get(position);
        RelativeLayout relativeLayout = holder.getView(R.id.relativeLayout);
        ImageView imageView_head = holder.getView(R.id.imageView_head);
        TextView textView_title = holder.getView(R.id.textView_title);
        TextView textView_time = holder.getView(R.id.textView_time);
    }
});
RecyclerViewHelper recyclerViewHelper = new RecyclerViewHelper(oThis, recyclerView);
recyclerViewHelper.setListViewForVertical(recyclerAdapter);