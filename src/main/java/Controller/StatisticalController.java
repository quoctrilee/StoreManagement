package Controller;

import DAO.OrderDAO;
import Model.OrdersModel;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StatisticalController {
    private OrderDAO orderDAO;

    public StatisticalController() {
        orderDAO = new OrderDAO();
    }

    public Map<Date, Float> getTotalAmountByDate() {
        ArrayList<OrdersModel> orders = orderDAO.selectAll();
        Map<Date, Float> totalAmountByDate = new HashMap<>();

        for (OrdersModel order : orders) {
            Date orderDate = new Date(order.getOdersdate().getTime());
            float totalAmount = order.getTotalAmount();

            totalAmountByDate.put(orderDate, totalAmountByDate.getOrDefault(orderDate, 0f) + totalAmount);
        }

        return totalAmountByDate;
    }

    public void updateChartDataset(DefaultCategoryDataset dataset) {
        Map<Date, Float> totalAmountByDate = getTotalAmountByDate();
        dataset.clear();
        for (Map.Entry<Date, Float> entry : totalAmountByDate.entrySet()) {
            dataset.addValue(entry.getValue(), "Tổng tiền", entry.getKey());
        }
    }
}
